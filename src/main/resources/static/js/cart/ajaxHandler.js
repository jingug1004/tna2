/**
 * Created by yjlee on 2016. 4. 26..
 */
define(function () {
    var agent = JSON.parse(document.getElementById('agent').value);

    var AjaxHandler = {
        apis: {
            UPDATE_COUNT: function (cartId) {
                return '/ajax/carts/' + cartId + '/count/';
            },
            SET_CHECKOUT: '/checkout/',
            ADD_CARTS: '/ajax/carts',
            LOAD_BUNDLE: '/ajax/carts/bundle/',
            LOAD_SOLD_OUT: '/ajax/carts/soldOut/',
            LOAD_ALL_BUNDLE: '/ajax/carts/',
            LOAD_CARTS_BY_ITEM: '/ajax/carts/items/',
            SEARCH_DELIVERY_CHARGE_DISCOUNT_ITEMS: '/ajax/search/deliveryChargeDiscountItems',
            REMOVE_CARTS: '/ajax/carts/',
            REMOVE_CART: function (cartId) {
                return '/ajax/carts/' + cartId + '/';
            },
            LOAD_SAVE_POINT: '/ajax/point/save/'
        },
        getErrorMessage: function (error, defaultMessage) {
            return !!error.responseJSON && !!error.responseJSON.message ? error.responseJSON.message : defaultMessage;
        },
        updateItemCount: function (cartId, itemCount, cb) {
            var defaultMessage = '수량을 변경할 수 없습니다.';

            if (!cartId || !itemCount) {
                alert(defaultMessage);
                return cb('error');
            }

            var apiUrl = this.getUrl(this.apis.UPDATE_COUNT(cartId));

            $.ajax({
                type: "POST",
                url: apiUrl,
                data: {cartId: cartId, itemCount: itemCount},
                dataType: 'json'
            }).done(function (result) {
                return cb(null, result);
            }).error(function (result) {
                alert(AjaxHandler.getErrorMessage(result, defaultMessage));
                return cb(null, result);
            });
        },
        setCheckout: function (carts, cb) {
            var apiUrl = this.getUrl(this.apis.SET_CHECKOUT);
            $('.ajax_overlay').show();
            $.ajax({
                type: "POST",
                url: apiUrl,
                data: JSON.stringify({carts: carts}),
                contentType: 'application/json',
                dataType: 'json'
            }).done(function (result) {
                return cb(null, result);
            }).error(function (result) {
                var defaultMessage = '주문을 진행할 수 없습니다.';

                alert(AjaxHandler.getErrorMessage(result, defaultMessage));
                return cb(result);
            }).always(function () {
                $('.ajax_overlay').hide();
            });
        },
        addCarts: function (carts, cb) {
            if (!carts || carts.length === 0) {
                alert('상품을 선택해주세요.');
                return cb('error');
            }

            var apiUrl = this.getUrl(this.apis.ADD_CARTS);

            $.ajax({
                type: "POST",
                url: apiUrl,
                dataType: 'json',
                data: {carts: carts}
            }).done(function (result) {
                return cb(null, result);
            }).error(function (result) {
                var defaultMessage = '상품을 담을 수 없습니다.';

                alert(AjaxHandler.getErrorMessage(result, defaultMessage));

                return cb(result);
            });
        },
        loadBundle: function (cartIds, cb) {
            var apiUrl = this.getUrl(this.apis.LOAD_BUNDLE);

            $.ajax({
                type: "GET",
                url: apiUrl,
                data: {cartIds: cartIds.join()},
                contentType: 'application/json',
                dataType: 'json',
                cache: false
            }).done(function (result) {
                return cb(null, result);
            }).error(function (result) {
                return cb(result);
            });
        },
        loadAll: function (cb) {
            var apiUrl = this.getUrl(this.apis.LOAD_ALL_BUNDLE);

            $.ajax({
                type: "GET",
                url: apiUrl,
                contentType: 'application/json',
                dataType: 'json',
                cache: false
            }).done(function (result) {
                return cb(null, result);
            }).error(function (result) {
                return cb(result);
            });

        },
        loadSoldOut: function (page, pageSize, cb) {
            var apiUrl = this.getUrl(this.apis.LOAD_SOLD_OUT);

            $.ajax({
                type: "GET",
                url: apiUrl,
                data: {page: page, pageSize: pageSize},
                contentType: 'application/json',
                dataType: 'json',
                cache: false
            }).done(function (result) {
                return cb(null, result);
            }).error(function (result) {
                return cb(result);
            });
        },
        loadCartsByItem: function (itemIds, cb) {
            var apiUrl = this.getUrl(this.apis.LOAD_CARTS_BY_ITEM);

            $.ajax({
                type: "GET",
                url: apiUrl,
                contentType: 'application/json',
                dataType: 'json',
                data: {itemIds: itemIds.join()},
                cache: false
            }).done(function (result) {
                return cb(null, result);
            }).error(function (result) {
                return cb(result);
            });
        },
        searchDeliveryChargeDiscountItems: function (params, cb) {
            var MAX_ITEM_LIST_SIZE = 5

            if (!params.deliveryWarehouseId) {
                alert('추천 상품이 없습니다.')
                return cb(null, {items: []})
            }

            // var apiData = {
            //     deliveryWarehouseId: params.deliveryWarehouseId,
            //     price: params.price,
            //     itemIds: params.itemIds || [],
            //     limit: params.limit || MAX_ITEM_LIST_SIZE,
            //     offset: params.offset || 0
            // };
            var apiUrl = this.getUrl(this.apis.SEARCH_DELIVERY_CHARGE_DISCOUNT_ITEMS);

            $.ajax({
                type: 'GET',
                url: apiUrl,
                contentType: 'application/json',
                dataType: 'json',
                data: params,
                cache: false
            }).done(function (result) {
                return cb(null, result);
            }).error(function (result) {
                alert('추천 상품을 가져 올 수 없습니다.')
                return cb(result);
            });
        },
        removeCarts: function (carts, cb) {
            if (!carts || carts.length === 0) {
                alert('장바구니를 선택해주세요.');
                return cb('error');
            }

            var apiUrl = this.getUrl(this.apis.REMOVE_CARTS);
            var cartIds = [];

            for (var i = 0; i < carts.length; i++) {
                cartIds.push(carts[i].cartId);
            }

            $.ajax({
                type: "DELETE",
                url: apiUrl,
                data: {cartIds: cartIds},
                dataType: 'json'
            }).done(function (result) {
                try {
                    AjaxHandler.setGTM(carts, 'Remove from Cart');
                    AjaxHandler.setSegment(carts);

                    return cb(null, result);
                } catch (e) {
                    return cb(null, result);
                }
            }).error(function (result) {
                var defaultMessage = '삭제할 수 없습니다.';
                alert(AjaxHandler.getErrorMessage(result, defaultMessage));
                return cb(result);
            });
        },
        removeCart: function (cart, cb) {
            if (!cart) {
                alert('장바구니를 선택해주세요.');
                return cb('error');
            }

            var apiUrl = this.getUrl(this.apis.REMOVE_CART(cart.cartId));

            $.ajax({
                type: "DELETE",
                url: apiUrl,
                dataType: 'json'
            }).done(function (result) {
                try {
                    AjaxHandler.setGTM([cart], 'Remove from Cart');
                    AjaxHandler.setSegment([cart]);

                    return cb(null, result);
                } catch (e) {
                    return cb(null, result);
                }

            }).error(function (result) {
                var defaultMessage = '삭제할 수 없습니다.';

                alert(AjaxHandler.getErrorMessage(result, defaultMessage));

                return cb(result);
            });
        },
        loadSavePoint: function (cost, cb) {
            if (!cost || Number(cost) <= 0) {
                return cb(null, {savePoint: 0});
            }

            var apiUrl = this.getUrl(this.apis.LOAD_SAVE_POINT);

            $.ajax({
                type: "GET",
                url: apiUrl,
                data: {cost: cost},
                contentType: 'application/json',
                dataType: 'json',
                cache: false
            }).done(function (result) {
                return cb(null, result);
            }).error(function (result) {
                // var defaultMessage = '적립 예정 포인트를 가져 올 수 없습니다.';
                // alert(AjaxHandler.getErrorMessage(result, defaultMessage));
                return cb(result);
            });
        },
        getUrl: function (apiUrl) {
            if (navigator.appVersion.indexOf("MSIE 8.") != -1) {
                apiUrl = 'https://' + location.host + apiUrl;
            }

            return apiUrl;

        },
        setGTM: function (carts, event) {
            var products = [];

            for (var i = 0; i < carts.length; i++) {
                var cart = carts[i];
                products.push({               // Provide product details in a productFieldObject.
                    'id': cart.itemId,                   // Product ID (string).
                    'name': cart.title, // Product name (string).
                    'brand': '',
                    'category': '',                // Product brand (string).
                    'variant': '',               // Product variant (string).
                    'price': cart.price,
                    'quantity': cart.itemCount()
                });
            }

            dataLayer.push({
                'event': event,
                'ecommerce': {
                    'currencyCode': 'KRW',
                    'add': {
                        'products': products
                    }
                }
            });
        },
        setSegment: function (carts) {
            // Segment Analytics Code
            for (var i = 0; i < carts.length; i++) {
                var item = carts[i];

                // Segment Product Removed from Cart
                analytics.track('Cart Removed', {
                    id: item.productId,
                    item_id: item.itemId,
                    quantity: item.itemCount(),
                    container_id: item.cartId
                }, {
                    anonymousId: agent.anonymousId
                });
            }
        }
    };

    return AjaxHandler;
});
