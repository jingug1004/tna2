/**
 * Created by yjlee on 2016. 4. 26..
 */
define(['./Product','./BundleCost', '../ajaxHandler', '../util', '../lodash.min'], function(Product, BundleCost, AjaxHandler, util, _) {
    var Bundle = function(bundle) {
        var self = this;

        self.bundleCost = ko.observable(new BundleCost(bundle));
        self.changed = ko.observable(false);
        self.tags = ko.observableArray(bundle.tags);

        var products = _.map(bundle.products, function (product) {
            return new Product(product);
        });

        self.products = ko.observableArray(products);
        self.checked = ko.observable('checked');
        self.removed = ko.observable(false);
        self.shortfallPriceForFreeDelivery = ko.observable(bundle.shortfallPriceForFreeDelivery)
        self.deliveryWarehouseId = bundle.deliveryWarehouseId;
        self.deliveryBundleType = bundle.deliveryBundleType;
        self.deliveryPartnerId = bundle.deliveryPartnerId;

        self.eventHandler = {
            loadBundle: function(addCartIds) {
                var cartIds = [];

                if (!!addCartIds) {
                    cartIds = cartIds.concat(addCartIds)
                }

                _.forEach(self.products(), function (product) {
                    _.forEach(product.items(), function (item) {
                        if (!!item.checked()) {
                            cartIds.push(item.cartId);
                        }
                    });
                });
                
                if (cartIds.length === 0) {
                    self.setBundleCost(0, 0);
                    return true;
                }

                AjaxHandler.loadBundle(cartIds, function (err, result) {
                    if (!! err) {
                        window.location.reload();
                        return true;
                    }

                    if (! result.bundles || result.bundles.length > 1 || !result.bundles[0]) {
                        return window.location.reload();
                    }

                    if (result.disabledCount > 0) {
                        alert('품절된 상품이 ' + result.disabledCount + '개 있습니다.');
                        return window.location.reload();
                    }

                    self.tags(result.bundles[0].tags)
                    self.shortfallPriceForFreeDelivery(result.bundles[0].shortfallPriceForFreeDelivery)

                    self.setBundleCost(result.itemCost, result.deliveryCharge);

                    if (!!addCartIds) {
                        self.eventHandler.addProduct(result.bundles[0], addCartIds)
                    }
                    return true;
                });
                
                return true;
            },
            productClick: function(data) {
                _.forEach(data.items(), function(item) {
                    item.checked(data.checked());
                });
                
                self.eventHandler.loadBundle();
                return true;
            },
            itemClick: function(parent) {
                var checkedAll = true;
                var unCheckedAll = true;

                if (!! parent.items() && parent.items().length > 0) {
                    _.forEach(parent.items(), function(item) {
                        if (item.checked()) {
                            unCheckedAll = false;
                        } else {
                            checkedAll = false;
                        }
                    });

                    if (unCheckedAll) {
                        parent.checked(false);
                    } else if (checkedAll) {
                        parent.checked(true);
                    }
                }
                self.eventHandler.loadBundle();
                return true;
            },
            changeItemCount: function(data) {
                data.ui.itemCountSelect.cssClass('');
                data.ui.itemCountInput.style.color('black');
                data.ui.itemCountInput.style.border('');

                if (!data.ui.itemCountSelect.visible()) {
                    data.itemCount(data.ui.itemCountInput.value());
                }

                if (data.itemCount() >= 10) {
                    data.ui.itemCountSelect.visible(false);
                    data.ui.itemCountInput.value(data.itemCount());
                } else {
                    data.ui.itemCountSelect.visible(true);
                }

                data.beforeCount = data.itemCount();
                data.originalCount = data.originalItemCount();

                AjaxHandler.updateItemCount(data.cartId, data.itemCount(), function (err, result) {
                    if (!!err) {
                        return window.location.reload();
                    }

                    if (data.isOutOfRange()) {
                        data.ui.itemCountSelect.options.remove(function (option) {
                            return !!option.disable;
                        });

                        data.isOutOfRange(false);
                    }

                    // Segment 호출 (2017.02.14)
                    try {
                        $(window).trigger('cartSegmentEvent_itemCountChange',[data]);
                    } catch(e) {}

                    self.eventHandler.loadBundle();
                    return true
                });
            },
            removeCart: function(item, product) {
                if (! item) {
                    return alert('장바구니를 선택해주세요.');
                }

                AjaxHandler.removeCart(item, function(err, result) {
                    if (err) {
                        return window.location.reload();
                    }

                    product.items.remove(item);

                    self.products.remove(function(product) {
                        return product.items().length === 0;
                    });

                    if (self.products().length === 0) {
                        self.removed(true);
                    }

                    // Segment 호출 (2017.02.14)
                    try {
                        $(window).trigger('cartSegmentEvent_deleteOne',[item]);
                    } catch(e) {}

                    self.eventHandler.loadBundle();
                    
                    return true;
                });
            },
            addCarts: function(items) {
                AjaxHandler.addCarts(items, function (err, result) {
                    if (err) {
                        return alert(err)
                    }

                    var itemIds = _.map(items, 'itemId');

                    console.log('start load cart item')
                    AjaxHandler.loadCartsByItem(itemIds, function (err, result) {
                        if (err) {
                            return false;
                        }

                        console.log('results', result)

                        var cartIds = _.map(result.carts, 'id');

                        console.log('cartIds', cartIds);
                        self.loadBundle(cartIds);
                    })
                })
            },
            addProduct: function(bundle, newCarts) {
                self.tags(bundle.tags);
                var bundleItems = []

                _.forEach(bundle.products, function(product) {
                    bundleItems = bundleItems.concat(product.items)
                })

                _.forEach(newCarts, function(cartId) {
                    var addedItem = _.find(bundleItems, {'cartId': cartId})

                    if (!!addedItem) {
                        var product = _.find(self.products(), {productId: addedItem.productId});

                        if (!!product) {
                            product.addCart(addedItem)
                        } else {
                            var newProduct = _.find(bundle.products, {id: addedItem.productId})
                            self.products.push(new Product(newProduct));
                        }
                    }
                })

                self.shortfallPriceForFreeDelivery(bundle.shortfallPriceForFreeDelivery);
            }
        };
        
        self.setBundleCost = function(itemCost, deliveryCharge) {
            self.bundleCost().itemCost(itemCost);
            self.bundleCost().deliveryCharge(deliveryCharge);
        };

        ko.computed(function() {
            var allUnchecked = true;

            _.forEach(self.products(), function (product) {
                _.forEach(product.items(), function (item) {
                    if (!!item.checked()) {
                        allUnchecked = false;
                    }
                });
            });

            if (allUnchecked) {
                self.setBundleCost(0, 0);
                self.tags([]);
                self.shortfallPriceForFreeDelivery(0);
            }

            return true;
        })
        
    };
    
    return Bundle;
});
