var loc1, loc2, loc3;
var defaultDepth1;

function getMenuInfo(){
	// get location
	loc1 = $('.loc_1').text();
	loc2 = $('.loc_2').text();
	loc3 = $('.loc_3').text();
}

function menuInit(){

	getMenuInfo();

	//gnb
	$('#gnb ul li').each(function(i){
		if( $(this).find('a').text() == loc1 ){
			$(this).find('a').addClass('active');
		}
	});
	var gnbAll = $('#header .gnb_all');
	$('#gnb').on({
		'mouseenter' : function() {
			gnbAll.stop().animate({ 'height' : 317 }, 200);
		},
		'mouseleave' : function() {
			gnbAll.stop().animate({ 'height' : 0 }, 200);
		}
	});
	//lnb
	$('#lnb ul li').each(function(i){
		var lnbText = $(this).find('> a').text();
		if( lnbText == loc2 || lnbText == loc3 ){
			$(this).find('> a').addClass('on');
		}
	});
}

$(function(){

	userAgentCheck();

	menuInit();

	/*====================================
	 * lnb ���̼���
	 =====================================*/
	 $( window ).on( 'load', function() {
		$( '.lnb_wrap' ).height( $( '#contents' ).height() );
	});

	/*====================================
	 * page tab
	 =====================================*/
	// $('.tabs ul li a').on('click', function(e){
		// e.preventDefault();
		// var tabIndex =  $('.tabs ul li a').index(this);
		// $('.tabs ul li a').removeClass('active');
		// $(this).addClass('active');
	// });

	/*====================================
	 * faq
	 =====================================*/
	$( '.faq_list .faqs a' ).on( 'click', function( e ) {

		e.preventDefault();

		if ( $( this ).parent().hasClass( 'active' ) ) {
			$( this ).parent().removeClass( 'active' );
			$( this ).parent().next( 'dd' ).removeClass( 'active' );
		} else {
			$( '.faq_list .faqs dt' ).removeClass( 'active' );
			$( '.faq_list .faqs dd' ).removeClass( 'active' );
			$( this ).parent().addClass( 'active' );
			$( this ).parent().next( 'dd' ).addClass( 'active' );
		}

	});

	/*====================================
	 * ��ܹ�� �ݱ�
	 =====================================*/

	var topBanner = $( '#wrap > .banner' ),
		topCheck = $( '#top_check' ),
		topBtn = topBanner.find( 'button' );

	if ( $.cookie( 'hideBanner' ) == 'true' ) {
		topBanner.css({ height : 0}, 300 );
	}

	topBtn.on( 'click', function() {
		if ( topCheck.prop( 'checked' ) ) {
			$.cookie( 'hideBanner', 'true', { expires: 1, path : '/' } );
		} else {
			$.cookie( 'hideBanner', 'true', { path : '/'} );
		}
		topBanner.stop().animate({ height : 0 }, 300 );
	});


	/* �Ǳ�� */
	var tabs = $( '.tabs a' ),
		sections = $( '.tab_section' );

	tabs.on( 'click', function( e ) {
		tabs.removeClass( 'active' );
		sections.removeClass( 'on' );

		$( this ).addClass( 'active' );
		sections.eq( tabs.index( this ) ).addClass( 'on' );

		if ( !$( this ).parents( '.tabs' ).hasClass( 'faq_tabs' ) ) {
			console.log('a');
			e.preventDefault();
		}

		$( '.lnb_wrap' ).height( $( '#contents' ).height() );
	});

	/* ������������ - ������ ���� �� */
	var serviceTabs = $( '.sub_tab a' ),
		serviceSections = $( '.sub_tab_section' );

	serviceTabs.on( 'click', function( e ) {
		e.preventDefault();
		serviceTabs.removeClass( 'on' );
		serviceSections.removeClass( 'on' );

		$( this ).addClass( 'on' );
		serviceSections.eq( serviceTabs.index( this ) ).addClass( 'on' );
	});


	/*
	 * �˾��������� ��ũ��Ʈ
	 */

	/* �Ǳ�� - �˾��� �ӽ� */
	var popTabs = $( '.tab a' ),
		popSections = $( '.tab_section' );

	popTabs.on( 'click', function( e ) {
		e.preventDefault();
		popTabs.removeClass( 'on' );
		popSections.removeClass( 'on' );

		$( this ).addClass( 'on' );
		popSections.eq( popTabs.index( this ) ).addClass( 'on' );
	});
	/* ����������ȸ �˾� ������¡ */
	/*
	var resizeWindow = function( el ) {
		window.resizeTo( 656, el.height() + 100 );
	}

	var payTabs = $( '.payments .tab a' ),
		contents = $( '.payments' );

	payTabs.on( 'click', function() {
		setTimeout( function() {
			resizeWindow( contents );
		}, 100 );
	});
	*/

	/* �޷� �˾� */
	var startCalendar = $( '.term_start' ),
		endCalendar = $( '.term_end' );

	startCalendar.each(function() {
		$( this ).datepicker({
			 showOn: 'button',
			 buttonImage: '../images/common/btn_calendar.png',
			 duration: 'fast',
			 dateFormat: 'yymmdd',
			 buttonText:'���۳�¥ ����',
			 firstDay:1
    });
	});

	endCalendar.each(function() {
		$( this ).datepicker({
			 showOn: 'button',
			 buttonImage: '../images/common/btn_calendar.png',
			 duration: 'fast',
			 dateFormat: 'yymmdd',
			 buttonText:'���ᳯ¥ ����',
			 firstDay:1
    });
	});

	var checkSunday = function() {
			$( '.ui-datepicker-week-end' ).each(function() {
				if ( $( this ).prev( '.ui-datepicker-week-end' ).length > 0 ) {
					$( this ).addClass( 'sunday' );
				}
			});
	}

	$( '.ui-datepicker-trigger' ).on( 'click', checkSunday );
	$( 'body' ).on( 'mouseenter', '.ui-datepicker-header', checkSunday );

	/* �˾� ��� ��� */
	var popTermsTitle = $( '.terms dt a' );
		popTermsText = $( '.terms dd' );

	popTermsTitle.on( 'click', function( e ) {
		e.preventDefault();
		var crtText = popTermsText.eq( popTermsTitle.index( this ) );

		if ( crtText.hasClass( 'on' ) ) {
			crtText.removeClass( 'on' );
			$( this ).parents( 'dt' ).removeClass( 'on' );
		} else {
			$( '.terms dt' ).removeClass( 'on' );
			popTermsText.removeClass( 'on' );
			$( this ).parents( 'dt' ).addClass( 'on' );
			popTermsText.eq( popTermsTitle.index( this ) ).addClass( 'on' );
		}
	});

	/*
	 * �ε��� ������
	 */

	/* ���κ��־� */
	(function () {
		var visuals = $( '#visual .visual_wrap > ul' ),
			visualImg = visuals.find( 'li' ),
			visualTimer = 0,
			visualNav = $( '#visual .nav' );

		if ( visualImg.length == 1 ) {
			visualToggle.hide();
			visualNav.hide();
			return false;
		}

		visuals.data('crt', 0);

		var defaultStyle = {
				'margin-top' : 0
			},
			hideStyle = {
				'margin-top' : -518
			},
			readyStyle = {
				'margin-top' : 518
			};

		visualImg.each(function () {
			var self = $(this);
			if(!self.hasClass('current')) {
				self.css(readyStyle);
			}
		});

		if ( visualImg.length > 1 ) {
			var visualBtns = '';
			visualBtns += '<ul>'
			for( var i = 0, len = visualImg.length; i < len; i++ ){
				visualBtns += '<li><button type="button">visual' + i + '</button></li>';
			}
			visualBtns += '</ul>'
			visualBtns += '<button type="button" class="toggle">���/�Ͻ�����</button>'
			visualNav.append( $( visualBtns) );
		}

		var visualNavBtn = $( '#visual .nav li button' );
		visualNavBtn.eq( 0 ).addClass( 'on');
		visualNavBtn.on( 'click', function () {
				var self = $(this),
					newNum = visualNavBtn.index(self),
					crtNum = visuals.data('crt');

				if ( newNum != crtNum ) {
					setPosition( crtNum, newNum );

					if ( newNum < crtNum ) {
						slideAction( 'reverse' );
					} else {
						slideAction();
					}

					visualNavBtn.eq( crtNum ).removeClass('on');
					self.addClass('on');

					clearInterval( visualTimer );
					visualTimer = 0;
					$( '#visual .toggle' ).addClass( 'off' );
				}
				return false;
		});

		function setPosition( crtNum, newNum ) {
			visualImg.eq( crtNum ).removeClass( 'current' )

			visualImg.eq( crtNum ).addClass( 'hide' );
			visualImg.eq( newNum ).addClass( 'current' )

			visuals.data('crt', newNum);
		}

		function slideAction ( dir ) {
			if ( dir ) {
				var hide = readyStyle,
					ready = hideStyle;
			} else {
				var hide = hideStyle,
					ready = readyStyle;
			}

			visualImg.filter( '.hide' ).stop().animate( hide, 600 ).removeClass( 'hide' );

			visualImg.filter( '.current' ).css( ready ).stop().animate( defaultStyle, 600 );
		}

		function autoSlide() {
			var crtNum = visuals.data('crt'),
				newNum = crtNum + 1;

				newNum = newNum > visualImg.length - 1  ? 0 : newNum;
			//visualNavBtn.eq(newNum).trigger('click');
			setPosition( crtNum, newNum );
			slideAction();

			visualNavBtn.eq( crtNum ).removeClass('on');
			visualNavBtn.eq( newNum ).addClass('on');
		}

		$( '#visual .toggle' ).on( 'click', function() {
			if ( visualTimer ) {
				clearInterval( visualTimer );
				visualTimer = 0;
				$( this ).addClass( 'off' );
			} else {
				visualTimer = setInterval(autoSlide, 5500);
				$( this ).removeClass( 'off' );
			}
		});

		visualTimer = setInterval(autoSlide, 5500);
	})();

	$( window ).on({
		'load' : userAgentCheck,
		'resize' : userAgentCheck
	});

	function checkResize() {
		var crtWidth = $( window ).width();
		if ( crtWidth < 1200 ) {
			var tx = ( 1800 - crtWidth ) / 2 + 20;
			if ( tx > 415 ) { tx = 415 }
			$( '#visual .nav' ).css({ 'right' : tx });
			$( '.quick_menu' ).hide();
		} else {
			$( '#visual .nav' ).css({ 'right' : 322 });
			$( '.quick_menu' ).show();
		}
	}

	function userAgentCheck(){
		var filter = "win16|win32|win64|mac|macintel";
		if( navigator.platform ){
			if( filter.indexOf( navigator.platform.toLowerCase() ) < 0 ){
				$('.quick_menu').css({'display': 'none'});
			} else {
				checkResize();
			}
		}
	}

	/* �ε��� ������ ���콺 ���� */
	var mainCont = $( '#contents .main_contents .section' ),
		mainContList = $( '#contents .main_contents .section .hover' );
	mainCont.on({
		'mouseenter' : function() {
			$( this ).find( '.hover' ).stop().animate({ 'opacity' : 1 });
		},
		'mouseleave' : function() {
			$( this ).find( '.hover' ).stop().animate({ 'opacity' : 0 });
		}
	});
	mainContList.css({ 'opacity' : 0 });

	/* ���޴� �׼� */
	(function () {
		var qMenu = $('.quick_menu'),
			banner = $( '.banner' ),
			ty = $( '#visual' ).length == 1 ? 585 : 140,
			bHeight = 0;

		$( window ).on('scroll',function () {
			var tTop = $( this ).scrollTop(),
				newBHeight = banner.height(),
				initTy = ty;

			if ( bHeight != newBHeight ) {
				initTy = initTy + newBHeight;
			}

			if ( tTop > initTy ) {
				qMenu.addClass( 'fix' );
			} else {
				qMenu.removeClass( 'fix' );
			}
		});
	})();

	/* Ǫ�� �йи�, �귣�� ����Ʈ */
	var footerSites = $( '.footer .site_list' ),
		sitesOpen = false;
	$( '.footer .brand > a, .footer .family > a' ).on( 'click', function( e ) {
		e.preventDefault();
		footerSites.removeClass( 'on' );
		$( this ).next( 'ul' ).addClass( 'on' );

		sitesOpen = true;
	});
	$( '.footer .brand, .footer .family' ).on( 'mouseleave', function( e ) {
		sitesOpen = false;
	});
	$( document ).on( 'click', function () {
		if ( !sitesOpen ) {
			footerSites.removeClass( 'on' );
		}
	});

	/* ���� ���� �ڸ��� */
	$( '.aside .news li:not(:first-child) a, .aside .news .first .title' ).each(function (){
		var text = $( this ).text();
		if( text.length > 24) { $( this ).text( text.substring( 0, 24 ) + '...' ) };
	});


	/* �α��� ��ư �ͽ��� ��� */
	var filter2 = "win16|win32|win64|mac|macintel";
	var ua = window.navigator.userAgent;
	var msie = ua.indexOf("MSIE");
	if( navigator.platform ){
		if( filter2.indexOf( navigator.platform.toLowerCase() ) < 0 ){
			return false;
		} else {

			if ( navigator.userAgent.indexOf('Trident/7.0') > -1) {
				return false;
			} else if ( msie < 0 ) {
				// �α��� ����
				$( '#header .btn_login' )[0].onclick = null;
				$( '#header .btn_login' ).on( 'click', function() {
					alert( '�����Ͻ� ����� ���ͳ��ͽ��÷η� ������ �� ���� �Ұ��� ����Դϴ�.\n��뿡 ������ ��� �˼��մϴ�.' );
					return false;
				});

				//�������� ��ȸ ����
				if ( $( '.lookup .btn_find a' ).length > 0 ) {
					$( '.lookup .btn_find a' )[0].onclick = null;
					$( '.lookup .btn_find a' ).on( 'click', function() {
						alert( '�����Ͻ� ����� ���ͳ��ͽ��÷η� ������ �� ���� �Ұ��� ����Դϴ�.\n��뿡 ������ ��� �˼��մϴ�.' );
						return false;
					});
				}
			}

		}
	}

});