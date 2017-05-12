(function ($) {
    $(function () {
            try {

                analytics.trackLink($("[segment-event='segmentLoginBtn']"), 'Button Clicked', {
                    category: 'Account',
                    label: 'Login Button Clicked'
                });

                analytics.trackLink($("[segment-event='segmentLogoutBtn']"), 'Button Clicked', {
                    category: 'Account',
                    label: 'Logout Button Clicked'
                });

                analytics.trackLink($("[segment-event='segmentSignupBtn']"), 'Button Clicked', {
                    name: '회원가입'
                });

                $("[segment-event='segmentLogoutBtn']").click(function () {
                    analytics.reset();
                });
            }
            catch (e) {
                console.log(e);
            }
        }
    );
})(jQuery);