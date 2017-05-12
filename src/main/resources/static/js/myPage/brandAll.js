(function() {
    var lastId,
        $brandContent = $('.brand-all-container')
        $brandNav = $('.brand-all-nav_list'),
        navItems = $brandNav.find('.brand-all-nav_list-item'),
        scrollItems = navItems.map(function(){
            var item = $($(this).attr("href"));
            if (item.length) { return item; }
        });

    $.each(navItems, function(index) {
        var str = $(this).text();
        var pattern = /[ㄱ-ㅎ]/;
        if (!(pattern.test(str))) {
            $(this).addClass('morpheme');
        }
    });

    navItems.on('click', function(){
        var href = $(this).attr('href'),
            offsetTop = href === '#' ? 0 : $(href).position().top + 1;

        $brandContent.animate({
            scrollTop: offsetTop
        }, 300);

        return false;
    });

    $brandContent.scroll(function(){
        var fromTop = $(this).scrollTop();
        var scrollHeight = $('.brand-all').height();
        var scrollPosition = $('.brand-all-inner').outerHeight() + $('.brand-all-inner').position().top;

        var cur = scrollItems.map(function(){
            if ($(this).position().top < fromTop)
                return this;
        });

        cur = cur[cur.length-1];

        var id = cur && cur.length ? cur[0].id : '';

        if ( (scrollHeight - scrollPosition) / scrollHeight === 0 ) {
            // id = scrollItems[scrollItems.length-1].attr('id');
            id = $('.brand-all-block').last().attr('id');
        } else if ( fromTop <= parseInt($('.brand-all-block').css('padding-top')) ) {
            // id = scrollItems[0].attr('id');
            id = $('.brand-all-block').first().attr('id');
        }

        if (lastId !== id) {
            lastId = id;
            navItems
                .parent().removeClass('focus')
                .end().filter("[href='#" + id + "']").parent().addClass('focus');
        }
    });
})();