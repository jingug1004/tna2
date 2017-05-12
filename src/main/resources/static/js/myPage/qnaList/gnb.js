(function() {
    $('.category').click(function() {
        $('#lnbOver').toggle();
        var categoryStatus = $('#lnbOver').css('display');
        if( categoryStatus === 'block') {
            $('#categoryIcon').attr('class', 'categoryIcon_02 wide dp fR');
            return false;
        }
        $('#categoryIcon').attr('class', 'categoryIcon_02 dp fR');
    });

    $('.lnbClose').click(function() {
        $('#lnbOver').toggle();
        $('#categoryIcon').attr('class', 'categoryIcon_02 dp fR');
    });

    $('.newGnbTab.brand').hover(
        function() {
            $(this).addClass('over');
        }, function() {
            $(this).removeClass('over');
        }
    );

    $(function () {
        try {
            var navClickedProperties = function (el) {
                return {
                    path: window.location.pathname,
                    url: window.location.href,
                    category: $(el).data("nav-type"),
                    label: $.trim($(el).text())
                };
            };
            analytics.trackLink($("[segment-event='Nav Clicked']"), "Nav Clicked", navClickedProperties);

            var mainTabClickProperites = function(el) {
                return {
                    name: '상단메뉴_' + $.trim($(el).text())
                };
            };

            analytics.trackLink($("[segment-event-ex='Main Tab Clicked']"), "Main Tab Clicked", mainTabClickProperites);

            var promotionClickedProperties = function(el) {
                return {
                    path: window.location.pathname,
                    url: window.location.href,
                    promotion_id: $(el).data("promotion-id"),           // Promotion ID
                    name: $(el).data("promotion-name"),                 // Promotion Name
                    label: $(el).data("promotion-name"),                // Label for GA
                    creative: $(el).data("promotion-creative"),         // Promotion Creative
                    link: $(el).data("promotion-link"),                 // Promotion Link
                    position: $(el).data("promotion-position"),         // Promotion Position
                    id: $(el).data("promotion-id"),                     // segment 2.0 추가사항
                    value1: $(el).data("promotion-link")                // segment 2.0 추가사항
                };
            };

            analytics.trackLink($("[segment-event='Promotion Clicked']"), "Promotion Clicked", promotionClickedProperties);

        }
        catch (e) {
            console.log(e);
        }
    });

})();