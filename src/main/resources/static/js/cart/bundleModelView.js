/**
 * Created by yjlee on 2016. 4. 26..
 */
define(['./model/Bundle', './util', './ajaxHandler', './lodash.min'], function(Bundle, util, AjaxHandler, _) {
    var BundleModelView = function() {
        var self = this;

        self.memeBundles = ko.observableArray([]).extend({ deferred: true });
        self.partnerBundles = ko.observableArray([]).extend({ deferred: true });
        self.checked = ko.observable(true);
        self.isInit = ko.observable(false);

        self.memeCost = {
            itemCost: ko.observable(0),
            deliveryCharge: ko.observable(0),
            totalCost: ko.observable(0)
        }

        self.partnerCost = {
            itemCost: ko.observable(0),
            deliveryCharge: ko.observable(0),
            totalCost: ko.observable(0)
        }

        self.costInfo = {
            itemCost: ko.observable(0),
            deliveryCharge: ko.observable(0),
            totalCost: ko.observable(0),
            savingPoint: ko.observable(0)
        };
        self.checkedItemCount = ko.observable();

        self.ui = {
            checkbox: {},
            checkoutButton: {}
        };
        
        self.eventHandler = {
            checkAll: function() {
                if (! self.checked()) {
                    self.costInfo.itemCost(0);
                    self.costInfo.deliveryCharge(0);
                    self.costInfo.totalCost(0);
                    self.costInfo.savingPoint(0);

                    return true;
                }

                AjaxHandler.loadAll(function(err, result) {
                    if (err) {
                        window.location.reload();
                        return true;
                    }

                    self.init(result.bundles.memebox, result.bundles.partner);

                    return true;
                });
                
                return true;
            },
            removeCarts: function() {
                var memeRemoves = getRemoves(self.memeBundles());
                var partnerRemoves = getRemoves(self.partnerBundles());

                var carts = _.map(memeRemoves.concat(partnerRemoves), function(removeItem) {
                    return removeItem.item;
                });
                
                if (carts.length === 0) {
                    return alert('장바구니를 선택해주세요.');
                }

                AjaxHandler.removeCarts(carts, function(err, result) {
                    if (err) {
                        window.location.reload();
                        return true;
                    }

                    var dealList = [];
                    _.map(memeRemoves.concat(partnerRemoves), function(removeItem) {
                        dealList.push(removeItem.item.productId);
                        return removeItem.item.removed = true;
                    });

                    // Segment 호출 (2017.02.14)
                    try {
                        $(window).trigger('cartSegmentEvent_deleteAll',[dealList.join(',')]);
                    } catch(e) {}

                    _.forEach(memeRemoves.concat(partnerRemoves),function(removeItem) {
                        var removed = true;

                        _.forEach(removeItem.product.items(), function(item) {
                            if (!item.removed) {
                                removed = false;
                            }
                        });

                        removeItem.product.removed = removed;
                    });

                    _.forEach(memeRemoves.concat(partnerRemoves), function(removeItem) {
                        var removed = true;

                        _.forEach(removeItem.bundle.products(), function(product) {
                            if (!product.removed) {
                                removed = false;
                            }
                            
                            product.items.remove(function(item) {
                                return item.removed;
                            })
                        });

                        removeItem.bundle.products.remove(function(product) {
                            return product.removed;
                        })

                        removeItem.bundle.removed(removed);
                    });

                    if (self.memeBundles().length === 0 && self.partnerBundles().length === 0) {
                        return window.location.reload();
                    }
                });
                                
                return true;
            },
            checkout: function() {
                var carts = self.getCheckoutCart(self.partnerBundles().concat(self.memeBundles()));
                
                if (carts.length === 0) {
                    return alert('장바구니를 선택해주세요.');
                }

                AjaxHandler.setCheckout(carts, function(err, result) {
                    if (err) {
                        window.location.reload();
                        return true;
                    } else {
                        $('body').trigger('clickCheckoutSegment');

                        if (!!result.redirect) {
                            window.location.href = result.redirect
                        } else {
                            window.location.href = "/order/" + result.cid;
                        }
                    }
                });
            }
        };

        self.getCheckoutCart = function(bundles) {
            var carts = [];

            _.each(bundles, function(b) {
                _.each(b.products(), function(p) {
                    var freeGiftPackage = p.freeGiftPackage();

                    _.chain(p.items())
                        .filter(function(c) { return c.checked()})
                        .forEach(function(c) {
                            carts.push({id: c.cartId, options: {freeGiftPackage: freeGiftPackage}});
                        })
                        .value();
                });
            });

            return carts;
        };

        var getRemoves = function(bundles) {
            var removes = [];

            _.forEach(bundles, function(bundle) {
                _.forEach(bundle.products(), function(product) {
                    _.forEach(product.items(), function(item) {
                        if (item.checked()) {
                            removes.push({item: item, product: product, bundle: bundle});
                        }
                    })
                })
            })

            return removes;
        };

        self.init = function(memebox, partner) {
            if (!!memebox && memebox.length > 0) {
                self.memeBundles(self.mappedBundle(memebox));
            }

            if (!!partner && partner.length > 0) {
                self.partnerBundles(self.mappedBundle(partner));
            }

            if (!! memebox.length > 0 || !! partner.length > 0) {
                self.isInit(true);
            }

            return true;
        };

        self.promotionFreeGiftImage = function(totalCost ) {
            var promotion = $('.cart-promotion-freeGift-info__image').data('freegift')
            var upBanner = promotion.freeGift.banners[0];
            var downBanner = promotion.freeGift.banners[1];
            var targetPrice = promotion.freeGift.targetPrice;

            return  totalCost < targetPrice ? downBanner.src : upBanner.src;
        };

        self.promotionFreeGiftAlt = function(totalCost) {
            var promotion = $('.cart-promotion-freeGift-info__image').data('freegift')
            var upBanner = promotion.freeGift.banners[0];
            var downBanner = promotion.freeGift.banners[1];
            var targetPrice = promotion.freeGift.targetPrice;
            
            return totalCost < targetPrice ? downBanner.alt : upBanner.alt;
        };

        self.calculateCostInfo = function() {
            var memeItemCost = 0;
            var memeDeliveryCharge = 0;

            if (!! self.memeBundles()) {
                _.forEach(self.memeBundles(), function(bundle) {
                    memeItemCost += bundle.bundleCost().itemCost();
                    memeDeliveryCharge += bundle.bundleCost().deliveryCharge();
                });

                self.memeCost.itemCost(memeItemCost);
                self.memeCost.deliveryCharge(memeDeliveryCharge);
                self.memeCost.totalCost(memeItemCost + memeDeliveryCharge);
            }

            var partnerItemCost = 0;
            var partnerDeliveryCharge = 0;

            _.forEach(self.partnerBundles(), function(bundle) {
                partnerItemCost += bundle.bundleCost().itemCost();
                partnerDeliveryCharge += bundle.bundleCost().deliveryCharge();
            });

            self.partnerCost.itemCost(partnerItemCost);
            self.partnerCost.deliveryCharge(partnerDeliveryCharge);
            self.partnerCost.totalCost(partnerItemCost + partnerDeliveryCharge);
            //if (memeItemCost + partnerItemCost != self.costInfo.itemCost()) {
                AjaxHandler.loadSavePoint(memeItemCost + partnerItemCost, function(err, result) {
                    if (!! result) {
                        self.costInfo.savingPoint(util.formatCurrency(result.savePoint));
                    }
                    
                    return true;
                })
            //}
            
            self.costInfo.itemCost(memeItemCost + partnerItemCost);
            self.costInfo.deliveryCharge(memeDeliveryCharge + partnerDeliveryCharge);

            self.costInfo.totalCost(self.costInfo.itemCost() + self.costInfo.deliveryCharge());
        };

        self.countCheckedItem = function() {
            var count = 0;
            var concatBundles = self.partnerBundles().concat(self.memeBundles());

            _.forEach(concatBundles, function(bundle) {
                _.forEach(bundle.products(), function(product) {
                    _.forEach(product.items(), function(item) {
                        if (item.checked()) {
                            count++;
                        }
                    });
                });
            });

            self.checkedItemCount(count);

        };

        self.mappedBundle = function(bundles) {
            if (!!bundles && bundles.length > 0) {
                var mappedData = [];

                _.forEach(bundles, function(bundle) {
                    mappedData.push(new Bundle(bundle));
                })
                // var mappedData = _.map(bundles, function(bundle) {
                //     return new Bundle(bundle);
                // });

                return mappedData;
            }

            return [];

        };
        
        self.formatCurrency = function(price) {
            return util.formatCurrency(price);
        };

        self.checked.subscribe(function() {
            var checkedVal = self.checked();

            _.forEach(self.memeBundles(), function(bundle) {
                _.forEach(bundle.products(), function(product) {
                    product.checked(checkedVal);

                    _.forEach(product.items(), function(item) {
                        item.checked(checkedVal);
                    })
                })
            });

            _.forEach(self.partnerBundles(), function(bundle) {
                _.forEach(bundle.products(), function(product) {
                    product.checked(checkedVal);

                    _.forEach(product.items(), function(item) {
                        item.checked(checkedVal);
                    })
                })
            });
            
            return true;
        });

        ko.computed(function() {
            if (self.memeBundles().length > 0 || self.partnerBundles().length > 0) {

                self.calculateCostInfo();
                self.countCheckedItem();

                self.memeBundles.remove(function(bundle) {
                    return bundle.removed();
                });

                self.partnerBundles.remove(function(bundle) {
                    return bundle.removed();
                });
            }

            if (self.isInit() && self.memeBundles().length == 0 && self.partnerBundles().length == 0) {
                return  window.location.reload();
            }
        }, this);

        self.loadAllCarts = function() {
            AjaxHandler.loadAll(function(err, result) {
                if (err) {
                    return false;
                }
                self.init(result.bundles.memebox, result.bundles.partner);

                if (result.disabledCount > 0) {
                    $('#disabledCountText').html(result.disabledCount)
                    $('.toastSoldOut').trigger('show')
                }

                $('body').trigger('impressionSegment', [result.bundles])

                return true;
            });
        }
    };
    
    return BundleModelView;
});