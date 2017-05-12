/**
 * Created by yjlee on 2016. 4. 26..
 */
define(function() {
    var BundleCost = function(bundleCost) {
        var self = this;

        self.itemCost = ko.observable(parseInt(bundleCost.itemCost));
        self.deliveryCharge = ko.observable(parseInt(bundleCost.deliveryCharge));
        self.totalCost = ko.computed(function () {
            return self.itemCost() + self.deliveryCharge();
        });
    };
    
    return BundleCost;
});