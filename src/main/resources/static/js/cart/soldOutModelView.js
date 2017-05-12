/**
 * Created by yjlee on 2016. 4. 25..
 */
define(['./model/Product', './ajaxHandler', './lodash.min'], function(Product, AjaxHandler, _) {
    var SoldOutViewModel = function() {
        var self = this;

        self.pageSize = 10;
        self.closeShowLimit = 1;
        self.totalSoldOut = ko.observable(0);
        self.currPage = 0;
        self.checked = ko.observable(false);

        self.showLimit = ko.observable(self.closeShowLimit);
        self.soldOuts = ko.observableArray([]);

        self.ui = {
            loadMoreButton: {
                visible: ko.observable(false),
                ing: false
            },
            closeButton: {
                visible: ko.observable(false)
            },
            openButton: {
                visible: ko.computed(function() {
                    return self.closeShowLimit <= self.soldOuts().length && !self.ui.closeButton.visible() && !self.ui.loadMoreButton.visible();
                })
            },
            popup: {
                visible: ko.observable(false),
                close: function() {
                    if ($('body').hasClass('noScroll')) {
                        $('body').removeClass('noScroll').removeAttr('scroll');
                    }
                    self.ui.popup.visible(false);
                    return window.location.reload();
                }
            }
        };
        
        self.hasNext = true;

        self.eventHandler = {
            remove: function(parent, data) {
                var cartId = data.cartId;

                if (! cartId) {
                    return alert('장바구니를 선택해주세요.');
                }

                parent.items.remove(data);

                if (parent.items().length == 0) {
                   self.soldOuts.remove(parent);
                }
                
                AjaxHandler.removeCart(data, function(err, result) {
                    if (err) {
                        return window.location.reload();
                    }

                    if (self.soldOuts().length === 0 && self.hasNext) {
                        self.ui.loadMoreButton.visible(true);
                        self.ui.closeButton.visible(false);
                    }

                    self.totalSoldOut(self.totalSoldOut() - 1);
                });
            },
            removeCarts: function() {
                var carts = []

                _.forEach(self.soldOuts(), function(soldOut) {
                    if (soldOut.checked()) {
                        _.forEach(soldOut.items(), function(item) {
                            carts.push(item);
                        })
                    }
                })

                if (carts.length === 0) {
                    return alert('장바구니를 선택해주세요.');
                }

                AjaxHandler.removeCarts(carts, function(err, result) {
                    if (err) {
                        window.location.reload();
                        return true;
                    }

                    self.totalSoldOut(self.totalSoldOut() - carts.length);

                    self.soldOuts.remove(function(soldOut) {
                        return soldOut.checked()
                    })

                    if (self.soldOuts().length == 0) {
                        self.ui.popup.close();
                    }
                })

                return true;
            },
            loadMore: function() {
                if (!! self.ui.loadMoreButton.ing) {
                    return alert('처리중입니다.')

                }

                self.ui.loadMoreButton.ing = true;
                AjaxHandler.loadSoldOut(self.currPage, self.pageSize, function(err, result) {
                    if (!! err) {
                        self.ui.loadMoreButton.visible(false);
                        self.ui.loadMoreButton.ing = false;

                        return false;
                    }

                    var soldOuts = result.soldOuts;

                    if (soldOuts.length === 0) {
                        self.ui.loadMoreButton.visible(false);
                    } else {
                        self.showLimit(self.pageSize);
                        self.ui.closeButton.visible(true);
                        self.appendSoldOut(soldOuts);
                    }

                    if (result.pageMetadata.totalPages > self.currPage + 1) {
                        self.currPage++;
                        self.ui.loadMoreButton.visible(true);
                    } else {
                        self.ui.loadMoreButton.visible(false);
                    }

                    self.ui.loadMoreButton.ing = false;

                    return true;
                });
            },
            toggleClose: function() {
                self.ui.closeButton.visible(!self.ui.closeButton.visible());
                self.closeShowLimit = 0;

                if (self.ui.closeButton.visible()) {
                    self.showLimit(self.pageSize);
                } else {
                    self.showLimit(self.closeShowLimit);
                }
            },
            startInit: function() {
                self.currPage = 0;

                AjaxHandler.loadSoldOut(0, 1, function(err, result) {
                    if (!! err) {
                        return false;
                    }

                    self.totalSoldOut(result.pageMetadata.totalPages);

                    AjaxHandler.loadSoldOut(0, self.pageSize, function(err, result) {
                        if (!! err) {
                            return false;
                        }

                        var soldOuts = result.soldOuts;

                        if (soldOuts.length === 0) {
                            alert('품절상품이 없습니다.');
                            return window.location.reload();
                        } else {
                            self.showLimit(self.pageSize);
                            self.ui.closeButton.visible(true);
                            self.setSoldOut(soldOuts);
                        }

                        if (result.pageMetadata.totalPages > 1) {
                            self.currPage++;
                            self.ui.loadMoreButton.visible(true);
                        }

                        $('body').addClass('noScroll').attr('scroll', 'no')
                        self.ui.popup.visible(true);
                        return true;
                    });
                });
            }
        };
        
        self.setSoldOut = function(soldOuts) {
            // if (! Array.isArray(soldOuts)) {
            //     return true;
            // }
            
            if (soldOuts.length === 0) {
                self.hasNext = false;
                return true;
            }

            var carts = _.map(soldOuts, function(c) {
                var product = new Product(c);
                product.checked(self.checked());

                return product;
            });

            self.soldOuts(carts.slice(0, self.pageSize));

            self.ui.loadMoreButton.visible(false);
        };

        self.appendSoldOut = function(soldOuts) {
            // if (! Array.isArray(soldOuts)) {
            //     return true;
            // }

            if (soldOuts.length === 0) {
                self.hasNext = false;
                return true;
            }

            var carts = _.map(soldOuts, function(c) {
                var product = new Product(c);
                product.checked(self.checked());

                return product;
            });

            self.soldOuts(self.soldOuts().concat(carts));
        };
        
        self.init = function(soldOuts, pageSize) {
            self.pageSize = pageSize;
            self.currPage = 0;

            self.setSoldOut(soldOuts);
        }

        self.checked.subscribe(function() {
            var checkedVal = self.checked();

            _.forEach(self.soldOuts(), function(soldOut) {
                soldOut.checked(checkedVal);

            });

            return true;
        });

        self.initLoad = function() {
            var pageSize = 2;

            AjaxHandler.loadSoldOut(0, pageSize, function(err, result) {
                if (err) {
                    return false;
                }

                var soldOuts = result.soldOuts;

                self.init(soldOuts, pageSize);
                return true;
            });
        }
    };
    
    return SoldOutViewModel;
});