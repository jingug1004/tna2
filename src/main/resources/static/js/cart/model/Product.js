/**
 * Created by yjlee on 2016. 4. 25..
 */
define(['./Cart', '../lodash.min'], function(Cart, _) {
    var Product = function(product) {
        var self = this;

        var mapData = _.map(product.items, function(item) {
            return new Cart(item);
        });
        
        self.productTitle = product.productTitle;
        self.productUrl = product.productUrl;
        self.thumbnailUrl = product.thumbnailUrl;
        self.items = ko.observableArray(mapData);
        self.checked = ko.observable(true);
        self.changed = ko.observable(false);
        self.isFreeGiftPackage = !! product.freeGiftPackage || false;
        self.freeGiftPackage = ko.observable('0');
        self.tags = product.tags;
        self.removed = false;
        self.productItemCost = ko.observable();
        self.normalLastCartId = ko.observable(0);
        self.additionLastCartId = ko.observable(0);
        self.hasAdditionalItem = !! product.hasAdditionalItem
        self.productId = product.id

        ko.computed(function() {
            var productIemCost = 0;
            _.forEach(self.items(), function(item) {
                productIemCost += item.itemCount() * item.price;

            });

            self.productItemCost(productIemCost);
        });
        
        self.ui = {
            checkbox: {},
            removeButton: {},
            freeGiftPackage:{
                attr: {
                    name: 'giftPacking_' + product.id
                }
            }
        };
        
        self.addCart = function(item) {
            self.items.push(new Cart(item))
        }

    };
    
    return Product;
});