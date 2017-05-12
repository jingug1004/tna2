define(['cart/soldOutModelView', 'cart/bundleModelView', 'cart/ajaxHandler', 'cart/itemListModelView'], function(SoldOutViewModel, BundleModelView, AjaxHandler, ItemListModelView) {
    var masterVM = function() {
        var self = this;

        ko.options.deferUpdates = true;
        self.bundleVM = new BundleModelView();
        self.soldOutVM = new SoldOutViewModel([]);
        self.soldOutPopUpVM =  new SoldOutViewModel([]);
        self.saveDeliveryChargeVM = new ItemListModelView();
        ko.applyBindings(self);

        self.soldOutPopUpVM.init([], 50);
    }
    
    return masterVM
});