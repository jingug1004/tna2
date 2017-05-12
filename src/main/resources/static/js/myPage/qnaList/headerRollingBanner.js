(function($) {
    $(function() {
        var rollingBannerLength = $('.topRollingBannerWrap').data('components-data');

        // Helper function for determining promotion impression based on banner ID
        function rollingBannerImpression(bannerId) {

            // Get banner element
            var bannerElement = $("#" + bannerId);

            // Check if promotion already viewed
            var promotion_viewed = bannerElement.parent().data("promotion-viewed");

            var isElementInViewport = function (el) {

                //special bonus for those using jQuery
                if (typeof jQuery === "function" || el instanceof jQuery) {
                    el = el[0];
                }

                var rect = el.getBoundingClientRect();

                return (
                    (rect.bottom > 0 && rect.bottom < (window.innerHeight || document.documentElement.clientHeight)) ||
                    (rect.top > 0 && rect.top < (window.innerHeight || document.documentElement.clientHeight)) /*or $(window).height() */
                );
            };

            // If not viewed and is visible in viewport
            if (!promotion_viewed && isElementInViewport(bannerElement)) {
                // Not viewed yet, gather metadata and send to segment analytics
                var promotion_name = bannerElement.parent().data("promotion-name");
                var promotion_id = bannerElement.parent().data("promotion-id");
                var promotion_creative = bannerElement.parent().data("promotion-creative");
                var promotion_link = bannerElement.parent().data("promotion-link");
                var promotion_position = bannerElement.parent().data("promotion-position");

                //Segment Analytics for Promotion Viewed v1.0
                //analytics.track("Promotion Viewed", {
                //    label: promotion_name,                              // Label for GA
                //    promotion_id: promotion_id,                         // Promotion ID (Name for now since ID is not useful)
                //    creative: promotion_creative,                       // Promotion Creative
                //    name: promotion_name,                               // Promotion Name
                //    position: promotion_position,                       // Promotion Position
                //    link: promotion_link                                // Promotion Link
                //});

                //Segment Analytics for Promotion Viewed v2.0
                analytics.track("Promotion Impression", {
                    id: promotion_id,                                   // v2.0
                    value1: promotion_link,                             // v2.0
                    promotion_id: promotion_id,                         // Promotion ID (Name for now since ID is not useful)
                    name: promotion_name,                               // Promotion Name
                    position: promotion_position                       // Promotion Position
                });

                // Set promotion viewed flag to true
                bannerElement.parent().data("promotion-viewed", true);
            }
        }

        if (rollingBannerLength > 0) {
            rollingBannerImpression('rollingBanner0');
        }


        function onOffMainVisual(index) {
            var rollingBannerId = 'rollingBanner' + index.toString();
            var offTargetRollingBanner = $('.rollingVisualOn');
            var onTargetRollingBanner = $('#' + rollingBannerId);

            offTargetRollingBanner.removeClass('rollingVisualOn');
            offTargetRollingBanner.addClass('rollingVisualOff');
            onTargetRollingBanner.removeClass('rollingVisualOff');
            onTargetRollingBanner.addClass('rollingVisualOn');

            $('.topRollingArrow.pre.tI').data('index', index - 1);
            $('.topRollingArrow.next.tI').data('index', index + 1);

            rollingBannerImpression(rollingBannerId);
        }

        $('.topRollingArrow.pre.tI').click(function () {
            var index = Number($(this).data('index'));

            if (index >= 0) {
                onOffMainVisual(index);
            } else {
                onOffMainVisual(rollingBannerLength - 1);
            }
        });

        $('.topRollingArrow.next.tI').click(function () {
            var index = Number($(this).data('index'));

            if (index < rollingBannerLength) {
                onOffMainVisual(index);
            } else {
                onOffMainVisual(0)
            }
        });
    });
})(jQuery);