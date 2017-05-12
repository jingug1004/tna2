/**
 * Created by yjlee on 2016. 4. 26..
 */
define(function() {
    
    return {
        formatCurrency: function(price) {
            return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
    };
});