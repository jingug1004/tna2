define(['./util', './ajaxHandler', './lodash.min', './model/Item'], function (util, AjaxHandler, _, Item) {
    var ItemModelView = function () {
        var self = this;

        self.items = ko.observableArray([]).extend({deferred: true});
        self.restItems = [];
        self.shortfallPriceForFreeDelivery = 0;
        self.formattedShortfallPriceForFreeDelivery = ko.observable(0);
        self.title = ko.observable('미미배송');
        self.bundle = {};
        self.visible = ko.observable(false);
        self.showSearchButton = ko.observable(true);
        self.onlyFree = false;
        self.inProgressItemId = 0;
        self.loadingInProgress = false;
        self.slider = undefined;

        self.viewconfig = {
            limit: 3,
            platform: 'web'
        };
        self.searchConfig = {
            limit: 5,
            next: true
        };

        self.initConfig = function (params) {
            self.viewconfig.limit = params.limit || self.viewconfig.limit;
            self.viewconfig.platform = params.platform || self.viewconfig.platform;
            self.searchConfig.limit = self.viewconfig.limit * 2;

            if (self.viewconfig.platform == 'mw') {
                initMobileWeb();
            } else if (self.viewconfig.platform == 'app') {
                initApp()
            } else {
                initWeb()
            }
        };

        function initWeb() {
            $('.moreBtn').click(function() {
                $(window).trigger('searchDeliveryChargeDiscountItems_loadMore', [getPublicProperties()]);
                $(window).trigger('cartSegmentEvent_loadMore', [getPublicProperties()]);
            })
        }

        function initMobileWeb() {
            $(window).on('hashchange', function () {
                self.render(decodeURI(window.location.hash));
            });
        }

        function initApp() {
            self.slider = $('.save-shipping-list-section').bxSlider({
                infiniteLoop: false,
                pager: false,
                onSliderLoad: function () {
                    if (self.items().length - 1 > 0) {
                        $('.bx-next').addClass('on');
                    }
                    $('.slide').css({'width' : $(document).width() * 0.85 + 'px', 'visibility' : 'visible'});
                },
                onSlideBefore: function ($slideElement, oldIndex, newIndex) {
                    if (newIndex >= self.items().length - 1) {
                        $('.bx-next').removeClass('on')
                    } else {
                        $('.bx-next').addClass('on')
                    }
                    if (newIndex <= 0) {
                        $('.bx-prev').removeClass('on')
                    } else {
                        $('.bx-prev').addClass('on')
                    }
                }
            });

            /* Layer Popup Close - iOS에서 작동하지 않음
            $(document).on('click', '.save-shipping-wrap-bg', function () {
                self.eventHandler.close();
            })*/
        }

        self.reset = function () {
            self.items([]);
            self.showSearchButton(true);
            self.visible(false);
            self.restItems = [];
            self.onlyFree = false;
            self.searchConfig.next = true;
            self.shortfallPriceForFreeDelivery = 0;
            self.formattedShortfallPriceForFreeDelivery(0);
            self.inProgressItemId = 0;
            self.scrollTopPosY = 0;

            if (self.viewconfig.platform == 'web') {
                $('.layerPopupContent .cartList:visible').scrollTop(0);
            } else if (self.viewconfig.platform == 'mw') {
                createQueryHash('close');
            } else if (self.viewconfig.platform == 'app') {
                self.scrollTopPosY = $('body').css('top').replace(/[^-\d\.]/g, '') * -1;
            }

            if ($('body').hasClass('noScroll')) {
                $('body').removeClass('noScroll').removeAttr('scroll');
                if (self.viewconfig.platform == 'app') {
                    $(document).scrollTop(self.scrollTopPosY);
                    $('body').removeAttr('style');
                }
            }
        };

        self.set = function (bundle) {
            self.bundle = bundle;
            self.shortfallPriceForFreeDelivery = bundle.shortfallPriceForFreeDelivery();
            self.formattedShortfallPriceForFreeDelivery(util.formatCurrency(bundle.shortfallPriceForFreeDelivery()));
            self.title(bundle.deliveryPartnerId == 16 ? '미미배송' : '업체배송');

            if (bundle.shortfallPriceForFreeDelivery() == 0 && bundle.bundleCost().deliveryCharge() > 0) {
                self.onlyFree = true;
            }
            if (self.viewconfig.platform == 'app') {
                $('body').css('top', ($(document).scrollTop() * -1) + 'px');
            }
        };

        self.eventHandler = {
            open: function (button, bundle) {
                self.reset();
                self.set(bundle);
                self.eventHandler.load(button);

                var productIds = getProductIds();

                try {
                    $(window).trigger('searchDeliveryChargeDiscountItems_open', [getPublicProperties()]);
                    $(window).trigger('cartSegmentEvent_open', [getPublicProperties(), productIds]);
                } catch(e) {}
            },
            close: function () {
                $(window).trigger('searchDeliveryChargeDiscountItems_close', [getPublicProperties()]);
                self.reset();
            },
            addCart: function (data, event) {
                var itemId = data.itemId;
                var DEFAULT_ITEM_COUNT = 1;
                var carts = [{
                    itemId: itemId,
                    itemCount: DEFAULT_ITEM_COUNT
                }];
                var $element = $(event.target);

                if (self.inProgressItemId == itemId) {
                    return alert('이미 선택된 상품입니다.')
                }

                self.inProgressItemId = itemId;
                
                data.itemCount = DEFAULT_ITEM_COUNT
                $(window).trigger('searchDeliveryChargeDiscountItems_add', [getPublicProperties(), data]);

                AjaxHandler.addCarts(carts, function (err, result) {
                    if (err) {
                        self.inProgressItemId = 0;
                        return false;
                    }

                    $(window).trigger('searchDeliveryChargeDiscountItems_addSuccess', [data]);

                    AjaxHandler.loadCartsByItem([itemId], function (err, result) {
                        if (err) {
                            return false;
                        }

                        var cartIds = _.map(result.carts, 'id');
                        
                        // Segment 호출 (2017.02.14)
                        try {
                            $(window).trigger('cartSegmentEvent_addCart', [cartIds, data]);
                        } catch(e) {}

                        self.reset();
                        self.bundle.eventHandler.loadBundle(cartIds);
                    })

                    $element.addClass('disabled').prop('disabled', true);
                })
            },
            load: function (button) {
                if (!!self.loadingInProgress) {
                    return false;
                }

                if (hasRestItems() || !self.searchConfig.next) {
                    return moveRestItemsToItems();
                }

                var
                    maxPrice = getMaxPrice(),
                    params = {
                        deliveryWarehouseId: self.bundle.deliveryWarehouseId,
                        minPrice: maxPrice,
                        shortfallPriceForFreeDelivery: self.shortfallPriceForFreeDelivery,
                        limit: self.searchConfig.limit,
                        onlyFree: self.onlyFree,
                        itemIds: getItemIds(maxPrice)
                    };

                if (isDealBundleType()) {
                    params.productId = _.head(self.bundle.products()).productId;
                }

                AjaxHandler.searchDeliveryChargeDiscountItems(params, function (err, result) {
                    if (err) {
                        self.reset();
                        return false;
                    }

                    var searchItemLength = result.items.length;

                    if (searchItemLength == 0 && !self.visible()) {
                        self.reset();
                        $(button.target).css('display', 'none');

                        $(window).trigger('searchDeliveryChargeDiscountItems_search',[getPublicProperties(maxPrice), {count: 0}]);
                        return alert('배송비 절약상품이 없습니다.')
                    }

                    if (self.items().length == 0){
                        initView();
                        $(window).trigger('searchDeliveryChargeDiscountItems_search',[getPublicProperties(maxPrice), {count: result.items.length}]);
                    }

                    if (searchItemLength < self.searchConfig.limit) {
                        self.searchConfig.next = false;
                    }

                    self.restItems = self.restItems.concat(result.items);

                    moveRestItemsToItems();

                    self.visible(true);
                })
            },
            redirectProductDetail: function(data) {
                if (!!data) {
                    $(window).trigger('searchDeliveryChargeDiscountItems_goProduct', [data]);
                }
            }
        }
        function initView() {
            if (self.viewconfig.platform == 'mw') {
                createQueryHash('open');
                $(document).scrollTop(0);
            } else {
                $('body').addClass('noScroll');
                $('body').addClass('noScroll').attr('scroll', 'no');
            }
        }

        self.afterRender = function(elements, data) {
            if ($('.save-shipping-list-section').children().length === self.items().length) {
                if (self.slider) {
                    self.slider.reloadSlider();
                }
            }
        }

        function hasRestItems() {
            return self.restItems.length >= self.viewconfig.limit;
        }

        function moveRestItemsToItems() {
            var moveSize = _.min([self.viewconfig.limit, self.restItems.length]);

            if (self.viewconfig.platform === 'web' && self.items().length >= 3) {
                moveSize = _.min([2, moveSize]);
            }

            for (var i = 0; i < moveSize; i++) {
                var item = self.restItems[i];
                self.items.push(new Item(item));

                // Segment 호출 (2017.02.14)
                try {
                    $(window).trigger('cartSegmentEvent_saveDeliveryItem', [item]);
                } catch(e) {}
            }

            self.restItems = self.restItems.slice(moveSize, self.restItems.length);

            if ((!self.searchConfig.next && self.restItems.length == 0) || self.items().length >= 5) {
                self.showSearchButton(false);
            }

            self.loadingInProgress = false;
        }

        function getMaxPrice() {
            return self.restItems.length > 0 ? _.last(self.restItems).price : self.items().length > 0 ? _.last(self.items()).price : 1;
        }

        function isDealBundleType() {
            return self.bundle.deliveryBundleType == 1;
        }

        function getItemIds(maxPrice) {
            var itemIds = [];

            _.forEach(self.bundle.products(), function (product) {
                itemIds = itemIds.concat(getItemIdsForHasOverMaxPrice(product.items(), maxPrice));
            });

            itemIds = itemIds.concat(getItemIdsForHasOverMaxPrice(self.items(), maxPrice));
            itemIds = itemIds.concat(getItemIdsForHasOverMaxPrice(self.restItems, maxPrice));

            return itemIds;
        }

        function getProductIds() {
            var productIds = [];

            _.forEach(self.bundle.products(), function (product) {
                productIds = productIds.concat(product.productId);
            });

            return productIds;
        }

        function getItemIdsForHasOverMaxPrice(items, maxPrice) {
            return _.chain(items)
                .filter(function (item) {
                    return item.price >= maxPrice
                })
                .map('itemId')
                .value();
        }

        function createQueryHash(event) {
            switch (event) {
                case 'open':
                    window.location.hash = '#saveShipping/';
                    $('#content').css('display', 'none');
                    $('footer').css('display', 'none');
                    break;
                case 'close':
                    window.location.hash = '';
                    $('#content').css('display', 'block');
                    $('footer').css('display', 'block');
                    break;
                default:
                    window.location.hash = '';
            }
        }

        //외부에서 참조가능한 항목만 가져오기
        function getPublicProperties(maxPrice) {
            maxPrice = maxPrice || 0;
            
            return {
                deliveryWarehouseId: self.bundle.deliveryWarehouseId,
                shortfallPriceForFreeDelivery: self.shortfallPriceForFreeDelivery,
                itemIds: getItemIds(maxPrice)
            }
        }

        self.render = function (url) {
            var temp = url.split('/')[0];

            if (temp == '#saveShipping' && !!self.visible()) {
                $('#content').css('display', 'none')
            } else {
                $('#content').css('display', 'block')
            }
        }
    }

    return ItemModelView;
});
