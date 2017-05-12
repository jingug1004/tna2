$(function () {
    try {
        var agent = JSON.parse(document.getElementById('agent').value)

        if (agent.platform == 'web') {
            $('.shoppingBtn.tI').click(function (e) {
                var cartItems = makeCartStr();

                analytics.track('click continue shopping', {
                    s_logType: 'clk',
                    s_cartItems: cartItems,
                    s_clickType: 'CTL',
                    s_clickAction: 'CLK',
                    session_id: getSessionCookie("segmentSessionID")
                });

                location.href = $(this).data('returnurl')
            });
        } else {
            $('.orderPageTitle').click(function (e) {
                var cartItems = makeCartStr();
                analytics.track('click continue shopping', {
                    s_logType: 'clk',
                    s_cartItems: cartItems,
                    s_clickType: 'CTL',
                    s_clickAction: 'CLK',
                    session_id: getSessionCookie("segmentSessionID")
                });

                location.href = $(this).data('returnurl')
            });
        }

        $('body').on('clickCheckoutSegment', function (event) {
            var cartItems = makeCartStr({onlyChecked: true});
            analytics.track('click check out', {
                s_logType: 'clk',
                s_cartItems: cartItems,
                s_clickType: 'CTB',
                s_clickAction: 'CLK',
                session_id: getSessionCookie("segmentSessionID")
            })
        });

        $('body').on('impressionSegment', function (event, bundleInfo) {
            var bundles = [];

            if(!!bundleInfo && !!bundleInfo.memebox && !!bundleInfo.partner){
                bundles = bundleInfo.memebox.concat(bundleInfo.partner)
            }

            var carts = [];
            var segment_products = [];
            var cartId = 0;
            var cartIds = [];

            for (var b = 0; b < bundles.length; b++) {
                var bundle = bundles[b]

                for (var p = 0; p < bundle.products.length; p++) {
                    var product = bundle.products[p];

                    for (var i = 0; i < product.items.length; i++) {
                        var item = product.items[i];
                        carts.push(item.cartId + ':' + item.productId + ':' + item.itemId + ':' + item.itemCount + ':' + item.price);

                        segment_products.push({
                            id: product.id,                 // Product ID
                            name: product.productTitle,     // Product Name
                            variant: item.title,            // Product Variant Name
                            sku: item.itemId,               // Product Variant ID
                            price: item.price,              // Product Price
                            currency: 'KRW',                // Currency Korean Won
                            quantity: item.itemCount,       // Quantity
                            brand: '',                      // TODO: brand is not being stored in item model
                            category: ''                    // TODO: product category not being stored in item model
                        });

                        cartId = item.cartId;               // set Cart ID
                        cartIds.push(cartId);

                        analytics.track('Item Impression', {
                            id: product.id,
                            name: product.productTitle,
                            item_id: item.itemId,
                            item_name: item.title,
                            quantity: item.itemCount,
                            container_id: cartId
                        }, {
                            anonymousId: agent.anonymousId
                        });
                    }
                }
            }

            var strCarts = carts.join(',');

            analytics.page({
                path: window.location.pathname,
                url: window.location.href,
                s_logType: 'imp',
                s_cartItems: strCarts,
                session_id: getSessionCookie("segmentSessionID"),
                platform: agent.platform
            });

            // Cart Viewed
            analytics.track('Cart Viewed', {
                container_id: cartIds.join(',')
            }, {
                anonymousId: agent.anonymousId
            });
        });

        function makeCartStr(params) {
            var itemList = [];

            if (agent.platform == 'web') {
                itemList = $('.bundles .itemList');
            } else {
                itemList = $('.itemList .wrapDeal .option li');
            }

            var carts = [];

            for (var i = 0; i < itemList.length; i++) {
                var tr = itemList[i];
                var data = $(tr).data();
                var addable = true;

                if (!!params && !!params.onlyChecked && !data.checked) {
                    addable = false
                }

                if (addable) {
                    carts.push(data.cartid + ':' + data.productid + ':' + data.itemid + ':' + data.itemcount + ':' + data.price)
                }
            }

            return carts.join(',')
        }

        /**
         *  배송비 절약 상품 ga
         */
        var searchDeliveryChargeDiscountEventCategory = '장바구니_PC'

        if (agent.platform == 'app') {
            searchDeliveryChargeDiscountEventCategory = '장바구니_App'
        } else if (agent.platform == 'mw') {
            searchDeliveryChargeDiscountEventCategory = '장바구니_Mobile'
        }

        var searchDeliveryChargeDiscountItemsEvent = {
            search: function(event, params, searchResult) {
                ga('send', {
                    hitType: 'event',
                    eventCategory: searchDeliveryChargeDiscountEventCategory,
                    eventAction: '배송비 절약상품_결과여부',
                    eventLabel: searchResult.count > 0 ? '결과 있음' : '결과 없음'
                });

                analytics.track('Deliverycharge Discount Popup List',{
                    s_logType: 'impDelivery',
                    s_deliveryWarehouseId: params.deliveryWarehouseId,
                    s_shortfallPriceForFreeDelivery : params.shortfallPriceForFreeDelivery,
                    s_itemIds: (params.itemIds).join(','),
                    s_transactionCount: searchResult.count
                });
            },
            goProduct: function(event, params) {
                ga('send', {
                    hitType: 'event',
                    eventCategory: searchDeliveryChargeDiscountEventCategory,
                    eventAction: '배송비 절약상품_상품상세이동',
                    eventLabel: params.productId + '_' + params.productTitle + '_' + params.itemName
                });

                window.location.href = params.productUrl
            },
            add: function(event, searchData, item) {
                ga('send', {
                    hitType: 'event',
                    eventCategory: searchDeliveryChargeDiscountEventCategory,
                    eventAction: '배송비 절약상품_담기_시도',
                    eventLabel: item.productId + '_' + item.productTitle + '_' + item.itemName
                });
                
                analytics.track('Deliverycharge Discount Popup List Add Cart',{
                    s_logType: 'clk',
                    s_deliveryWarehouseId: searchData.deliveryWarehouseId,
                    s_shortfallPriceForFreeDelivery : searchData.shortfallPriceForFreeDelivery,
                    s_itemIds: (searchData.itemIds).join(','),
                    s_clickType : 'BAI',
                    s_clickAction : 'CCI',
                    s_items : item.productId + ':' + item.itemId + ':' + item.itemCount + ':' + item.price
                });
            },
            addSuccess: function(event, params) {
                ga('send', {
                    hitType: 'event',
                    eventCategory: searchDeliveryChargeDiscountEventCategory,
                    eventAction: '배송비 절약상품_담기_성공',
                    eventLabel: params.productId + '_' + params.productTitle + '_' + params.itemName
                });
            },
            loadMore: function(event, params) {
                ga('send', {
                    hitType: 'event',
                    eventCategory: searchDeliveryChargeDiscountEventCategory,
                    eventAction: '배송비 절약상품_더보기',
                    eventLabel: '더보기 클릭'
                });

                analytics.track('Deliverycharge Discount Popup List Plus',{
                    s_logType: 'clk',
                    s_deliveryWarehouseId: params.deliveryWarehouseId,
                    s_shortfallPriceForFreeDelivery : params.shortfallPriceForFreeDelivery,
                    s_itemIds: (params.itemIds).join(','),
                    s_clickType : 'BAI',
                    s_clickAction : 'PLS'
                });
            },
            close: function(event, params) {
                ga('send', {
                    hitType: 'event',
                    eventCategory: searchDeliveryChargeDiscountEventCategory,
                    eventAction: '배송비 절약상품_이탈',
                    eventLabel: '이탈'
                });
            },
            open: function(event, params) {
                ga('send', {
                    hitType: 'event',
                    eventCategory: searchDeliveryChargeDiscountEventCategory,
                    eventAction: '배송비 절약상품_이탈',
                    eventLabel: '이탈'
                });

                analytics.track('Deliverycharge Discount Popup', {
                    s_logType: 'clk',
                    s_deliveryWarehouseId: params.deliveryWarehouseId,
                    s_shortfallPriceForFreeDelivery : params.shortfallPriceForFreeDelivery,
                    s_itemIds: (params.itemIds).join(','),
                    s_clickType : 'BAI',
                    s_clickAction : 'CLK'
                });
            }
        }

        $(".cartTopBanner").click(function(el){
            analytics.track("Promotion Clicked", {
                id: 'tvcf',
                name: 'TVCF Promotion',
                creative: $(el).find('img').attr('src'),
                link: $(el).find('a').attr('href'),
                position: 'Cart Top Banner'
            });
        });

        var cartSegmentEvent = {
            deleteAll : function(event, params) {
                var serviceName = '딜_삭제하기(WEB)';
                if (agent.platform == 'app') {
                    serviceName = '딜_삭제하기(App)';
                }

                try {
                    analytics.track('Button Clicked', {
                        id: params,
                        name: serviceName
                    });
                } catch(e) {}
            },
            deleteOne : function(event, params) {
                try {
                    analytics.track('Button Clicked', {
                        id: params.productId,
                        item_id: params.itemId,
                        name: '옵션_삭제'
                    });
                } catch(e) {}
            },
            itemCountChange : function(event, params) {
                try {
                    analytics.track('Button Clicked', {
                        item_id: params.itemId,
                        name: '옵션수량변경',
                        value1: params.originalCount,
                        value2: params.beforeCount
                    });
                } catch(e) {}
            },
            //saveDeliveryItem : function(event, params) {
            //    try {
            //        analytics.track('Item Impression', {
            //            id: params.productId,
            //            item_id: params.itemId,
            //            name: params.itemName
            //        });
            //    } catch(e) {}
            //},
            open : function(event, params, productId) {
                try {
                    analytics.track('Button Clicked', {
                        id: productId.join(','),
                        item_id: params.itemIds.join(','),
                        name: '배송비절약상품보기'
                    });
                } catch(e) {}
            },
            loadMore : function(event, params) {
                try {
                    analytics.track('Button Clicked', {
                        name: '배송비절약상품보기_더보기'
                    });
                } catch(e) {}
            },
            addCart : function(event, cartIds, params) {
                try {
                    analytics.track('Button Clicked', {
                        id: cartIds,
                        item_id: params.itemId
                    });
                } catch(e) {}
            }
        }

        $(window)
            .bind('searchDeliveryChargeDiscountItems_search', searchDeliveryChargeDiscountItemsEvent.search)
            .bind('searchDeliveryChargeDiscountItems_goProduct', searchDeliveryChargeDiscountItemsEvent.goProduct)
            .bind('searchDeliveryChargeDiscountItems_add', searchDeliveryChargeDiscountItemsEvent.add)
            .bind('searchDeliveryChargeDiscountItems_addSuccess', searchDeliveryChargeDiscountItemsEvent.addSuccess)
            .bind('searchDeliveryChargeDiscountItems_loadMore', searchDeliveryChargeDiscountItemsEvent.loadMore)
            .bind('searchDeliveryChargeDiscountItems_close', searchDeliveryChargeDiscountItemsEvent.close)
            .bind('searchDeliveryChargeDiscountItems_open', searchDeliveryChargeDiscountItemsEvent.open)

            .bind('cartSegmentEvent_deleteAll', cartSegmentEvent.deleteAll)
            .bind('cartSegmentEvent_deleteOne', cartSegmentEvent.deleteOne)
            .bind('cartSegmentEvent_itemCountChange', cartSegmentEvent.itemCountChange)
            //.bind('cartSegmentEvent_saveDeliveryItem', cartSegmentEvent.saveDeliveryItem)
            .bind('cartSegmentEvent_open', cartSegmentEvent.open)
            .bind('cartSegmentEvent_loadMore', cartSegmentEvent.loadMore)
            .bind('cartSegmentEvent_addCart', cartSegmentEvent.addCart)
    } catch (e) {
    }
});