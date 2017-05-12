define(function Cart() {
    var Cart = function(cart) {
        var self = this;

        self.itemId = cart.itemId;
        self.cartId = cart.cartId;
        self.productId = cart.productId;
        self.productUrl = cart.productUrl;
        self.productTitle = cart.productTitle;
        self.thumbnailUrl = cart.thumbnailUrl || '';
        self.title = cart.title;
        self.price = cart.price;
        self.itemType = cart.itemType;
        self.checked = ko.observable(true);
        self.itemCount = ko.observable(cart.itemCount || 0);
        self.originalItemCount = ko.observable(cart.itemCount || 0);
        self.removed = false;
        self.quantity = {
            max: cart.maxQuantity || 0,
            min: cart.minQuantity || 0
        };
        self.isInit = ko.observable(false);
        self.isLastCart = !! cart.isLastCart;
        self.isOutOfRange = ko.observable();
        self.isBest = !! cart.isBest;

        self.ui = {
            removeButton: {},
            itemCountSelect: {
                event: {
                    click: function(itemCount) {
                        self.originalItemCount(itemCount);
                    }
                },
                cssClass: ko.observable(''),
                options: [],
                visible: ko.observable(self.itemCount() < 10),
                value: self.itemCount()
            },
            itemCountChangeButton: {
                visible: ko.observable(false),
                event: {
                    click: function(data, changeItemCount) {
                        self.ui.itemCountChangeButton.visible(false);
                        
                        // if (self.itemCount() < 10) { 
                        //     self.ui.itemCountSelect.visible(true);
                        // }
                        
                        changeItemCount(data);
                        self.ui.itemCountAlert.visible(false);
                    },
                    show: function() {
                        self.ui.itemCountInput.style.border('solid 1px #ff5073')
                        self.ui.itemCountChangeButton.visible(true)
                    }
                }
            },
            itemCountInput: {
                change: function() {
                    self.ui.itemCountAlert.visible(false);
                    self.ui.itemCountInput.style.border('solid 1px #ff5073')

                    if (! Number(self.ui.itemCountInput.value())) {
                        self.ui.itemCountInput.value(self.quantity.min);
                        self.ui.itemCountAlert.text('최소');
                        self.ui.itemCountAlert.visible(true);
                    } else if (self.ui.itemCountInput.value() > self.quantity.max) {
                        self.ui.itemCountInput.value(self.quantity.max);
                        self.ui.itemCountAlert.text('최대');
                        self.ui.itemCountAlert.visible(true);
                    } else if (self.ui.itemCountInput.value() < self.quantity.min) {
                        self.ui.itemCountInput.value(self.quantity.min);
                        self.ui.itemCountAlert.text('최소');
                        self.ui.itemCountAlert.visible(true);
                    }

                    self.ui.itemCountChangeButton.visible(true);

                    return true;
                },
                style: {
                    color: ko.observable('#333333'),
                    border: ko.observable()
                },
                value: ko.observable(self.itemCount())
            },
            itemCountAlert: {
                visible: ko.observable(false),
                text: ko.observable('최대')
            }
        };

        ko.computed(function() {
//            if (self.ui.itemCountSelect.visible()) {
                self.ui.itemCountSelect.visible(self.itemCount() < 10);
//            }

            var checkOutOfRange = self.itemCount() < self.quantity.min || self.itemCount() > self.quantity.max;
            self.isOutOfRange(checkOutOfRange);
        })

        var initItemCount = function() {
            var options = [];

            if (self.isOutOfRange()) {
                options.push({value: cart.itemCount, text: cart.itemCount, disable: true});
                self.ui.itemCountSelect.cssClass('soldOut');
                self.ui.itemCountInput.style.color('red');
            }

            var maxQuantity = self.quantity.max > 10 ? 11 : self.quantity.max;

            for (var i = self.quantity.min ; i <= maxQuantity; i++) {
               // var text = i === 10 && self.quantity.max > 10 ? '10+' : i;
                var text = i === 11  ? '11+' : i;

                options.push({value: i, text: text, disable: false});
            }

            if (self.isOutOfRange()) {
                self.ui.itemCountSelect.options = ko.observableArray(options);
            } else {
                self.ui.itemCountSelect.options = options;
            }
            
            self.isInit(true)
        };

        self.setOptionDisable = function(option, item) {
            if (!! item) {
                ko.applyBindingsToNode(option, {disable: item.disable}, item);
            } else {
                ko.applyBindingsToNode(option, {disable: true}, item);
            }
        };

        initItemCount();
    };

    return Cart;
});