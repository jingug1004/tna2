Swag.registerHelpers(Handlebars);
var ProductViewLog = {
    viewBanner: function () {
        var scroll = $(window).scrollTop();
        var $view = $('.sideBannerR').find('.fixed');
        var $contentsArea = $('#container ');
        var contentsWidth = $contentsArea.offset().left + $contentsArea.width() + 31;

        if (scroll > 745) {
            $view.css({'position': 'fixed', 'top': '50px', 'left': contentsWidth});
        } else {
            //$view.css({position:'absolute', 'left': '0px', 'top':'518px'});
            $view.css({position: 'absolute', 'left': '0px', 'top': '675px'});
        }
    }
};
