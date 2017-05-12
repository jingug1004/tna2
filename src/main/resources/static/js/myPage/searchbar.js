var MEMEBOX = MEMEBOX || {};
var sb_q = "";
var retry;
var sp_query;
var $search;
SP = function(srp_url, door) {

  if (typeof SP === "object"){
    return SP;
  }
  this.params = this.getQueryString();
  this.srp_url = srp_url;
  srp_page_d_url = srp_url;
  if(this.isNull(srp_url)) {
    this.srp_url = "/";
    srp_page_d_url = "/";
  }
  if (!$("link[href*='searchBar.css']").length) {
    $('head').append('<link href="'+this.srp_url+'static/css/searchBar.css?v=wnrmsRo" rel="stylesheet"/>');
  }

  return this;
};
SP.prototype.isNull = function (q) {
  return (q == undefined || q == '');
};
SP.prototype.arraySize = function (arr) {
  if (this.isNull(arr)) return 0;
  return arr.length;
};
SP.prototype.openBestDeal = function (q, id, type) {
  var door = $('input[name="door"]').val();
  var qt = $('input[name="qt"]').val();
  try {
    if(this.isNull(retry)) retry="";

    analytics.track('Keyword BestDeal Clicks', {
      s_logType: 'clk',
      s_requestedPage: door,
      s_queryType: qt,
      s_keywords: q,
      s_clickType: 'SBD',
      s_clickAction: 'CLK',
      s_dealId: id,
      s_framePosition: type,
      s_retry: retry
    });
  }catch (e) {
    console.log(e);
  }
  window.location = "//www.memebox.com/product/" + id+"?q="+q;
};
SP.prototype.myTrim = function (q) {
  return q.replace(/^\s+|\s+$/gm,'');
};
SP.prototype.replaceHTMLSpecialChar = function (q) {
  if (this.isNull(q)) {return q;}
  q = q.toString();
  q = q.replace(/&/gi, "&amp;");
  q = q.replace(/</gi, "&lt;");
  q = q.replace(/>/gi, "&gt;");
  q = q.replace(/\"/gi, "&quot;");
  q = q.replace(/\'/gi, "&quot;");
  return q;
};
SP.prototype.replaceHTMLSpecialCharReverse = function (q) {
  if (this.isNull(q)) {return q;}
  q = q.toString();
  q = q.replace(/&amp;/gi, "&");
  q = q.replace(/&lt;/gi, "<");
  q = q.replace(/&gt;/gi, ">");
  q = q.replace(/&quot;/gi, "\"");
  q = q.replace("&quot;", /\'/gi);
  return q;
};

SP.prototype.getQueryString = function () {
  var query_string = {};
  var query = window.location.search.substring(1);
  var vars = query.split("&");
  for (var i=0;i<vars.length;i++) {
    var pair = vars[i].split("=");
    if (pair == "") {continue;}
    if (!pair[1]) {continue;}
    pair[1] = pair[1].replace(/\+/gi, " ");
    try {
      pair[1] = decodeURIComponent(pair[1]);
    } catch (e)  {
      console.log("url malform");
    }
    pair[1] = this.myTrim(pair[1]);
    pair[1] = pair[1].replace(/\s\s+/g, ' ');
    if (typeof query_string[pair[0]] === "undefined") {
      query_string[pair[0]] = this.replaceHTMLSpecialChar(pair[1]);
    } else if (typeof query_string[pair[0]] === "string") {
      var arr = [ query_string[pair[0]],this.replaceHTMLSpecialChar(pair[1]) ];
      query_string[pair[0]] = arr;
    } else {
      query_string[pair[0]].push(this.replaceHTMLSpecialChar(pair[1]));
    }
  }
  return query_string;
};
SP.prototype.filterLanding = function (q, qt, brd,  cat1, cat2) {
  $search.val(q);

  if (brd == 0) brd = '';
  if (cat1 == 0) cat1 = '';
  if (cat2 == 0) cat2 = '';
  $('input[name=cat1]').val(cat1);
  $('input[name=cat2]').val(cat2);
  $('input[name=cat3]').val('');
  $('input[name=brd]').val(brd);
  $('input[name=at]').val(3);

  var door = $('input[name="door"]').val();
  var qt = $('input[name="qt"]').val();
  var ct = "SBC";
  var cp = "CLK";
  if(!this.isNull(brd)) {ct = "SBB"; cp = "CLK";}
  if(this.isNull(retry)) retry="";

  analytics.track('Keyword Navigation Clicks', {
    s_logType: 'clk',
    s_requestedPage: door,
    s_queryType: qt,
    s_keywords: q,
    s_clickType: ct,
    s_clickAction: cp,
    s_category1: cat1,
    s_category2: cat2,
    s_category3: 0,
    s_brandId: brd,
    s_retry: retry});

  $("#searchForm").submit();
};
SP.prototype.goSearch = function (q, qt) {
  $search.val(q);
  var ct = "";
  var cp = "";
  if(qt == "popular") {ct = "SBP"; cp = "CLK";}
  else if(qt == "history"){ct = "SBH"; cp = "CLK";}
  else if(qt == "atc"){ct = "SBA"; cp = "CLK";}
  else if(qt == "related"){ct = "SBR"; cp = "CLK";}
  else if(qt == "realtime"){ct = "SHS"; cp = "CLK";}
  $( "input[name='enq']" ).val("");
  $("input[name='qt']").val(qt);
  $("input[name='cat1']").val(0);
  $("input[name='cat2']").val(0);
  $("input[name='cat3']").val(0);
  $("input[name='brd']").val("");
  var door = $('input[name="door"]').val();
  var qt = $('input[name="qt"]').val();
  if(this.isNull(retry)) retry="";

  analytics.track('Button Clicked', {
    s_logType: 'clk',
    s_requestedPage: door,
    s_queryType: qt,
    s_keywords: q,
    s_clickType: ct,
    s_clickAction: cp,
    s_retry: retry,
    name : q,
    value1 : ct});

  $("#searchForm").submit();
};
SP.prototype.checkIEVersion=function(v) {
  var ua = window.navigator.userAgent;
  var ie = false;
  for (; v >= 6; v--) {
    ie = ua.indexOf('MSIE '+v) >= 0;
    if (ie) {
      console.log('internet explorer '+v+' is not supported local Storage');
      break;
    }
  }
  return ie;
};
SP.prototype.commaSplit = function(n) {
  var txtNumber = '' + n;
  var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
  var arrNumber = txtNumber.split('.');
  arrNumber[0] += '.';
  do {
    arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2');
  }
  while (rxSplit.test(arrNumber[0]));
  if (arrNumber.length > 1) {
    return arrNumber.join('');
  } else {
    return arrNumber[0].split('.')[0];
  }
};
function SB (srp_url, door) {
  try {
    this.srp_url = srp_url;
    if(this.isNull(srp_url))
      this.srp_url = "//search.memebox.com/";
    this.params = SP.params;
    this.s_index = null;
  }catch (e ){
    console.log(e);
  }
}
SB = SP;
SB.prototype.appendSearchbar = function() {
  try {
    var searchBar =
      '<div class="searchBar-wrap">' +
      '  <div class="searchBar-inputBox-wrap">' +
      '    <form id="searchForm" class="searchBar-inputBox" method="GET" action=\"'+this.srp_url+'memebox\/search\" enctype="application/x-www-form-urlencoded" onsubmit="return SPexecuteSearch();">'+
      '      <label class="searchBar-inputBox-label">' +
      '        <span class="searchBar-inputBox-text">search bar</span>' +
      '        <input id="search" class="searchBox ui-autocomplete-input searchBar-inputBox-input" type="text" name="q" autocomplete="off" placeholder="" searchBarType="" landingParameter="">' +
      '        <span class="searchBar-inputBox-reset"  id="sb-q-rm" style="display: block;" ></span>' +
      '      </label>' +
      '      <button class="iconSearch searchBar-inputBox-submit" id ="executeSearchButton" type="submit">  검색하기  </button>'+
      '      <a class="iconSearch searchBar-inputBox-submit" id="landingPageButton" style="display:none;">  검색하기  </a>'+
      '      <input type="hidden" name="ab" value="1">' +
      '      <input type="hidden" name="qt" value="box">' +
      '      <input type="hidden" name="at" value="1">' +
      '      <input type="hidden" name="cat1" value="0">' +
      '      <input type="hidden" name="cat2" value="0">' +
      '      <input type="hidden" name="cat3" value="0">' +
      '      <input type="hidden" name="brd" value="">' +
      '      <input type="hidden" name="enq" value="">' +
      '      <input type="hidden" name="door" value="pc_main">' +
      '      <input type="hidden" name="recombar" value="0">';

    if (!this.isNull(this.params.cache)) {
      searchBar += '<input type="hidden" name="cache" value="' + this.params.cache + '">';
    }

    searchBar += '</form></div></div>';

    var tab=
        '<div id="tabs" class="suggest-layer" style="display: none;">' +
        '  <div id="sb-tab-selector" class="suggest-selector-wrap">' +
        '    <ol class="suggest-selector">' +
        '      <li class="suggest-selector-tab">' +
        '        <a id="realtime-trigger" class="suggest-selector-trigger selected">실시간검색어</a>' +
        '      </li>' +
        '      <li class="suggest-selector-tab">' +
        '        <a id="recent-trigger" class="suggest-selector-trigger">최근검색어</a>' +
        '      </li>' +
        '    </ol>' +
        '  </div>' +
        '  <div id="sb-keyword" class="suggest-recommendation-wrap">' +
        '    <div id="realtime_keyword" class="suggest-keyword-wrap suggest-keyword-ranking">' +
        '      <div class="exhibit"></div>' +
        '      <div class="keyword-ranking-wrap">' +
        '        <span class="keyword-ranking-date"></span>' +
        '        <ol class="keyword-ranking-list"></ol>' +
        '      </div>' +
        '    </div>' +
        '    <div id="recent_keyword" class="suggest-keyword-wrap suggest-keyword-recent">' +
        '      <ol class="keyword-recent-list"></ol>' +
        '      <div class="recommendation-keyword-recent-off" style="display: none;">' +
        '        최근 검색어가 꺼져 있습니다.<br><br>' +
        '      </div>' +
        '      <div class="recommendation-keyword-recent-menu">' +
        '        <a class="recommendation-keyword-recent-use-off" data-recent-use="false">검색저장끄기</a>' +
        '        <a class="recommendation-keyword-recent-use-on" data-recent-use="true">검색저장켜기</a>' +
        '        <span class="recommendation-keyword-recent-menu-split" style="display: none;">|</span>' +
        '        <a class="recommendation-keyword-recent-delete" style="display: none;">' +
        '          전체삭제' +
        '        </a>' +
        '      </div>' +
        '    </div>' +
        '  </div>' +
        '  <div id="sb-atc" class="suggest-result-wrap" style="display: none;">' +
        '    <div class="exhibit"></div>' +
        '    <ul class="keyword-autoComplete-wrap"></ul>' +
        '    <div class="keyword-quickLink-wrap">' +
        '      <span class="keyword-quickLink-info">바로보기</span>' +
        '      <ul class="keyword-quickLink-content"></ul>' +
        '    </div>' +
        '  </div>' +
        '  <div class="suggest-layer-menu">' +
        '    <a id="close-tabs" class="suggest-layer-menu-close" style="cursor: pointer;">닫기</a>' +
        '  </div>' +
        '</div>';

    $(".newSearchWrap").append(searchBar);
    $(".searchBar-wrap").append(tab);

    $("#keywords").append('<ol class="recommendation-keyword-recent-ol"><li class="recommendation-keyword-recent-li" style="display: none"><a class="recommendation-keyword-recent-keyword-trigger" onclick=""></a><span class="recommendation-keyword-recent-keyword-delete" onclick="">삭제</span></li></ol>');
    $("input[name=cat1]").val(this.params.cat1);
    $("input[name=cat2]").val(this.params.cat2);
    $("input[name=cat3]").val(this.params.cat3);
    $("input[name=cache]").val(this.params.cache);
    $("input[name=ab]").val(this.params.ab);
    $("input[name=enq]").val(this.params.enq);

    if (this.isSRPPage()) {
      if (this.isNull(sp_query)){
        $("#search").val(this.params.q);
      } else{
        $("#search").val(this.replaceHTMLSpecialCharReverse(sp_query));
      }

    }

    if (this.srp_url == "/") {
      $("input[name=door]").val("pc_sub");
    } else {
      $("input[name=door]").val("pc_main");
    }
    try {
      var open_banner = this.getStoreData("opb");
      if (open_banner == false) $("#topBanner").hide();
      else  this.setStoreData("opb", true);
      var sb = this;
      $('#closeTopBanner').click(function () {
        $("#topBanner").hide();
        sb.setStoreData("opb", false);
      });
    }catch (e) {
      console.log(e);
    }
    $search = $("#search");
  } catch(e) {
    console.log(e);
  }
};
SB.prototype.isSRPPage = function() {
  if (window.location.hostname == "search.memebox.com") return true;
  return false;
};
SB.prototype.convertEngToKor = function(args) {
  var engChosung = "rRseEfaqQtTdwWczxvg";
  var engChosungReg = "[" + engChosung + "]";
  var engJungsung = {k:0,o:1,i:2,O:3,j:4,p:5,u:6,P:7,h:8,hk:9,ho:10,hl:11,y:12,n:13,nj:14,np:15,nl:16,b:17,m:18,ml:19,l:20};
  var engJungsungReg = "hk|ho|hl|nj|np|nl|ml|k|o|i|O|j|p|u|P|h|y|n|b|m|l";
  var engJongsung = {"":0,r:1,R:2,rt:3,s:4,sw:5,sg:6,e:7,f:8,fr:9,fa:10,fq:11,ft:12,fx:13,fv:14,fg:15,a:16,q:17,qt:18,t:19,T:20,d:21,w:22,c:23,z:24,x:25,v:26,g:27};
  var engJongsungReg = "rt|sw|sg|fr|fa|fq|ft|fx|fv|fg|qt|r|R|s|e|f|a|q|t|T|d|w|c|z|x|v|g|";
  var regExp = new RegExp("("+engChosungReg+")("+engJungsungReg+")(("+engJongsungReg+")(?=("+engChosungReg+")("+engJungsungReg+"))|("+engJongsungReg+"))","g");
  var converter = function (args, cho, jung, jong) {
    return String.fromCharCode(engChosung.indexOf(cho) * 588 + engJungsung[jung] * 28 + engJongsung[jong] + 44032);
  };
  return args.replace(regExp, converter);
};

SB.prototype.setSelectedIndex = function(index) {
  var $ul = $(".keyword-autoComplete-wrap");

  if (!$ul.is(":visible")) {
    return;
  }

  if (index === null) {
    $ul.show();
    return;
  }

  var $li = $ul.find('li');

  if (index < 0) {
    index = $li.length - 1;
  }
  if (index >= $li.length) {
    index = 0;
  }
  $li.removeClass('ui-state-focus').eq(index).addClass('ui-state-focus');
  var keyword = $li.eq(index).data('keyword');

  console.log(keyword);
  console.log(this.myTrim($search.val()+''));
  if (keyword === this.myTrim($search.val()+'')) {
    this.setSelectedIndex(index + (index-this.s_index > 0 ? 1 : -1));
    return;
  }

  if (keyword) {
    $search.val(keyword);
  }
  this.s_index = index;
};

SB.prototype.bindEventAutocomplete = function() {
  var s_url = this.srp_url;
  var obj = this;
  var timeout = 50;
  $("input#search").on('keyup', function(e) {
    if (obj.timeout) {
      return;
    }
    $("#executeSearchButton").show();
    $("#landingPageButton").hide();

    var insert_q = obj.myTrim($search.val());
    if (obj.isNull(insert_q.length)) {
      $("#sb-q-rm").hide();
      $("#sb-atc").hide();
      $("#sb-tab-selector").show();
      $("#sb-keyword").show();
      $("#tabs").show();
      return;
    } else {
      $("#sb-q-rm").show();
      if (insert_q.length > 20) {
        return;
      }
    }
    if (e.keyCode > 40 || e.keyCode == 8) {
      var data = {"q": insert_q, "pg": 0};
      obj.timeout= setTimeout(function(){}, timeout);
      $.ajax({
        url: s_url+"memebox/atc",
        dataType: 'JSONP',
        jsonpCallback: "atcCallBack",
        data: data,
        success: function atcCallBack(result) {
          try {

            var hasFilter = false;
            var brd_size = obj.arraySize(result[2]);
            var cat_size = obj.arraySize(result[1]);
            var brd_atc_max = 1;
            var cat_atc_max = 3;
            var keyword_max = 10;

            if (brd_size + cat_size > 0){
              cat_atc_max = cat_atc_max - brd_size;
              keyword_max = keyword_max - (Math.min(cat_atc_max, cat_size) + brd_size) - 1;
            } else if (obj.arraySize(result[0]) + cat_size + brd_size == 0){
              $("#tabs").hide();
              return;
            }

            var quickLink = '';

            var atc_list = result[2];
            var atc;
            var query = '';
            if (!obj.isNull(atc_list)) {
              for (var i = 0; i < atc_list.length; i++) {
                if (brd_atc_max-- == 0) break;
                atc = atc_list[i];
                query = atc.keyword;
                quickLink +=
                    '<li class="keyword-quickLink-li-brand" onclick="SP.filterLanding(\'' + query + '\', \'atc\', \'' + atc.ids + '\', \'\', \'\')">' +
                    atc.filterName +
                    '</li>';
                hasFilter = true;
              }
            }
            atc_list = result[1];
            if (!obj.isNull(atc_list)) {
              for (var i = 0; i < atc_list.length; i++) {
                if (cat_atc_max-- == 0) break;
                atc = atc_list[i];
                query = atc.keyword;
                quickLink +=
                    '<li class="keyword-quickLink-li-category" onclick="SP.filterLanding(\''+query+'\', \'atc\', \'\', \''+atc.srlList[0]+'\', \''+atc.srlList[1]+'\')">' +
                    atc.filterName +
                    '</li>';
                hasFilter = true;
              }
            }

            var autocomplete = '';

            atc_list = result[0];

            for (var i = 0; i < atc_list.length; i++) {
              if (keyword_max-- == 0) break;
              if (i == 0) {
                var best_deal_q = atc_list[i].keyword;
                obj.getBestDeal(best_deal_q, '이 검색어의 BEST 상품', $("#sb-atc"), 'atc');
              }
              atc = atc_list[i];
              query = atc.keyword;
              var queryArray = query.replace(insert_q, insert_q+insert_q).split(insert_q);
              for (var i2 = 0; i2 < queryArray.length; i2++) {
                var splitedQuery = queryArray[i2];
                if (splitedQuery) {
                  splitedQuery = '<span class="keyword-autoComplete-side">'+splitedQuery+'</span>';
                } else if (i2 != 0 && i2 != queryArray.length-1) {
                  splitedQuery = '<span class="keyword-autoComplete-equal">'+insert_q+'</span>';
                }
                queryArray[i2] = splitedQuery;
              }
              autocomplete +=
                  '<li class="keyword-autoComplete" data-keyword="'+query+'" onclick="SP.goSearch(\''+query+'\', \'atc\')">' +
                  '  <a class="keyword-autoComplete-trigger">' +
                  queryArray.join('') +
                  '  </a>' +
                  '</li>';
            }
            $(".keyword-autoComplete-wrap").html(autocomplete);

            if (hasFilter) {
              $(".keyword-quickLink-content").html(quickLink);
              $(".keyword-quickLink-wrap").show();
            }
            else {
              $(".keyword-quickLink-wrap").hide();
            }

            $("#sb-tab-selector").hide();
            $("#sb-keyword").hide();
            $("#sb-atc").show();
            $("#tabs").show();
            obj.s_index = -1;
          } catch(e) {
            console.log(e);
          }
        },
        complete: function () {
          clearTimeout(obj.timeout);
          obj.timeout = undefined;
        }
      });
    }
  }).
  on('keydown', function(e){
    if (e.keyCode == 38 && obj.s_index !== null) {
      obj.setSelectedIndex(obj.s_index - 1);
      var keyword = $(".keyword-autoComplete-wrap").find('li').eq(obj.s_index).data('keyword');
      obj.getBestDeal(keyword, '이 검색어의 BEST 상품', $("#sb-atc"), 'atc');
    } else if (e.keyCode == 40 && obj.s_index !== null) {
      obj.setSelectedIndex(obj.s_index + 1);
      var keyword = $(".keyword-autoComplete-wrap").find('li').eq(obj.s_index).data('keyword');
      obj.getBestDeal(keyword, '이 검색어의 BEST 상품', $("#sb-atc"), 'atc');
    } else if (e.keyCode == 27 && obj.s_index !== null) {
      obj.setSelectedIndex(null);
      var keyword = $(".keyword-autoComplete-wrap").find('li').eq(obj.s_index).data('keyword');
      obj.getBestDeal(keyword, '이 검색어의 BEST 상품', $("#sb-atc"), 'atc');
    }
  });
};

SB.prototype.getBestDeal  = function (q, title, $target, type) {
  if (!q || !title || !$target) {
    return;
  }
  var obj = this;
  var s_url = this.srp_url+"memebox/atcdeal";
  $.ajax({
    url: s_url,
    type: "GET",
    dataType:'JSONP',
    jsonpCallback:"atcDeal",
    data: {"q": q},
    success: function (result) {
      try {
        var deal = result.documents[0];
        if (!deal) {
          return;
        }

        var template =
            '<div class="exhibit-header">' +
            '  <strong class="exhibit-keyWord">'+title+'</strong>' +
            '</div>' +
            '<ul class="memebox-deal-list memebox-deal-list-line-amount-1">' +
            '  <li class="memebox-deal-wrapper">' +
            '    <div class="memebox-deal deal_type_vertical_square_4">' +
            '      <div class="memebox-deal-info">' +
            '        <div class="memebox-deal-image-wrap">' +
            '          <span class="memebox-deal-badge badge-none"></span>' +
            '          <img class="memebox-deal-image" src="'+deal.thumbnailURI+'" alt="'+deal.title+'" style="width: 152px; height: 152px;">' +
            '        </div>' +
            '        <div class="memebox-deal-name">' +
            '          <strong class="memebox-deal-name-main">' +
            deal.title +
            '          </strong>' +
            '        </div>' +
            '        <p class="memebox-deal-price-wrap">';

        if (deal.discount_rate) {
          template +=
              '<strong class="memebox-deal-price-discount">' +
              '  <span class="memebox-deal-price-info">최대</span>' +
              '  <strong class="memebox-deal-price-value">'+deal.discount_rate+'</strong>' +
              '  <em class="memebox-deal-price-unit">%</em>' +
              '</strong>';
        }
        template +=
            '          <strong class="memebox-deal-price-result">' +
            '            <span class="memebox-deal-price-info">미미가격</span>' +
            '            <strong class="memebox-deal-price-value">'+obj.commaSplit(deal.sales_price)+'</strong>' +
            '            <em class="memebox-deal-price-unit">원</em>' +
            '          </strong>' +
            '        </p>' +
            '        <a class="memebox-deal-link" onclick="SPOpenBestDeal(\''+q+'\', '+deal.id+' , \'' + type + '\')">바로가기</a>' +
            '      </div>' +
            '    </div>' +
            '  </li>' +
            '</ul>';

        $target.find(".exhibit").html(template);

      } catch (e) {
        console.log(e);
      }
    }
  });
};
SB.prototype.executeSearch  = function () {
  var q = $search.val().replace(/^\s+|\s+$/gm,'');

  try {
    var searchBarType = $("#search").attr("searchBarType");

    if ((q === undefined || q == "" ) && searchBarType =="searchKeyword" ) {
      $search.val($("#search").attr("landingParameter"));
      $("input[name='recombar']").val("1");
    } else {
      $("input[name='recombar']").val("0");
    }

    if (q.length > 50) {
      q = this.replaceHTMLSpecialChar(q);
      return true;
    }
    var k_q = this.convertEngToKor(q);
    var convert = false;
    var $act = $(".keyword-autoComplete-wrap").find(".keyword-autoComplete");
    var actSize = $act.length;
    var searchForm = document.forms['searchForm'];
    var qt = $('input[name="qt"]').val();
    $('input[name="enq"]').val('');
    if (k_q != q && qt == "box") {
      for (var i = 0; i < actSize; i++) {
        var actQuery = $act.data('keyword');
        if (k_q === actQuery) {
          convert = true;
          $('input[name="enq"]').val(q);
          $search.val(actQuery);
        }
        if (q === actQuery) {
          if (convert == true)
            $('input[name="enq"]').val("");
          convert = true;
          $search.val(actQuery);
          break;
        }
      }
    }
    if (!convert && this.isChosung(k_q) && qt == "box") {
      for (var i = 0; i < actSize; i++) {
        var actQuery = $act.data('keyword');
        if (actQuery != undefined) {
          if (q.length == actQuery.length) {
            $search.val(actQuery);
            $('input[name="enq"]').val(q);
            break;
          }
        }
      }
    }
  } catch (e){
  } finally {
    this.storeKeyword(this.replaceHTMLSpecialChar(q));
  }

  return true;
};
SB.prototype.addHidden = function (theForm, key, value) {
  var input = document.createElement('input');
  input.type = 'hidden';
  input.name = key;
  input.value = value;
  $(theForm).append(input);
};
SB.prototype.bindEventSearchBox = function () {
  $('#sb-q-rm').click(function(){

    $('#search').val('');
    $('#sb-q-rm').hide();
    $('#tabs').hide();

    if ($search.attr("searchBarType") != "searchKeyword") {
      $("#executeSearchButton").hide();
      $("#landingPageButton").show();
    } else {
      $("#executeSearchButton").show();
      $("#landingPageButton").hide();
    }

    $search.keyup();
  });
  $('.iconSearch').click(function() {
    $( "input[name='cat1']" ).val('');
    $( "input[name='cat2']" ).val('');
    $( "input[name='cat3']" ).val('');
    $( "input[name='brd']" ).val('');
    $( "input[name='qt']" ).val("box");
  });
};

SB.prototype.isChosung = function(q) {
  var cCho  = [ 'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ' ];
  var cnt = q.length;
  var cho = false;
  for (var i = 0; i < cnt; i++) {
    if (jQuery.inArray(q[i], cCho) == -1) break;
    cho = true;
  }
  return cho;
};
SB.prototype.bindEventSearchbarClick = function () {
  $search.click(function () {
    if ($("#tabs").is(":visible")) return ;
    // $('#sb-atc').hide();
    // $("#sb-tab-selector").show();
    // $("#sb-keyword").show();
    $('#tabs').show();
  });
};
SB.prototype.realTimeKeywordLoad = function () {
  var obj = this;
  var spg_url = this.srp_url;
  $.ajax({
    url: spg_url+'memebox/rtk',
    dataType:'JSONP',
    jsonpCallback:"rtkCallBack",
    success: function (rtk) {
      if (!rtk) {
        return;
      }
      var list = rtk.list;
      $.each(list, function(index, value) {
        var template =
            '<li class="keyword-ranking-li">' +
            '  <a class="keyword-ranking-trigger" onclick="SPgoSearch(\'' + value.keyword + '\',\'realtime\')">' +
            '    <em class="keyword-ranking-value">'+value.rank+'</em>' +
            '    <strong class="keyword-ranking-text">'+value.keyword+'</strong>';

        if (value.differ == 'new') {
          template +=
              '<span class="keyword-ranking-direction-NEW">NEW</span>';
        } else if (value.differ == '0') {
          template +=
              '<span class="keyword-ranking-differ">-</span>';
        } else {
          var direction = value.differ*1 > 0 ? 'up' : 'down';
          template +=
              '<span class="keyword-ranking-direction-'+direction+'">'+direction+'</span>' +
              '<span class="keyword-ranking-differ">'+value.differ+'</span>'
        }
        template +=
            '  </a>' +
            '</li>';

        $(".keyword-ranking-list").append(template);
      });

      var time = rtk.time;
      $(".keyword-ranking-date").text(time);

      // bestdeal
      var randomIndex = Math.floor((Math.random() * list.length));
      var randomQ = list[randomIndex].keyword;
      obj.getBestDeal(randomQ, '실시간 검색어 BEST 상품', $("#realtime_keyword"), 'realtime');
    }}
  );
};
SB.prototype.getStoreData = function(key) {
  if (this.checkIEVersion(7)) {
    return "";
  }
  return store.get(key);
};
SB.prototype.setStoreData = function(key, value) {
  if (this.checkIEVersion(7)) {
    return;
  }
  store.set(key, value);
};
SB.prototype.getSortedKeywords  = function () {
  var sortable = [];
  $.each(this.getKeywordMap(), function(i, item) {
    sortable.push([item.keyword, item.date]);
  });
  sortable.sort(function (a,b) {
    return (new Date(a[1]) < new Date(b[1]) ? 1 : -1);
  });
  return sortable;
};
SB.prototype.getRecentKeywordSize = function () {
  var arr = [];
  for(var key in this.getKeywordMap()) {
    arr.push(key);
  }
  return arr.length;
};
SB.prototype.bindEventCloseAutocomplete = function () {
  $("body").click(function (event) {
      var targetClass = $(event.target).attr('class');
      if (targetClass == "keyword-recent-delete") return;
      if ($("#tabs").length > 0) {
        if (!$(".newSearchWrap").has(event.target).length) {
          $("#tabs").hide();
        }
      }
    }
  );
  // close button
  $("#close-tabs").click(function(event) {
    $("#tabs").hide();
  });
};
SB.prototype.bindEventRealTimeTabClick = function () {
  var obj = this;
  $("#realtime-trigger").on('click', function () {
    var door = $('input[name="door"]').val();
    var qt = $('input[name="qt"]').val();
    $("#realtime_keyword").show();
    $("#recent_keyword").hide();
    $("#sb-atc").hide();
    $(this).addClass('selected');
    $('#recent-trigger').removeClass('selected');

    try {
      if (obj.isNull(retry)) retry = "";

      analytics.track('Keyword Tab Clicks', {
        s_logType: 'clk',
        s_requestedPage: door,
        s_queryType: qt,
        s_clickType: 'SHS',
        s_clickAction: 'TAB',
        s_retry: retry});
    }catch (e ){
      console.log(e);
    }
  });
};
SB.prototype.appendRecentKeywordList = function () {
  var st = this.getStoreData("st");
  var $recentList = $("#recent_keyword").find(".keyword-recent-list");
  this.toggleRecentSave();
  if (!st) {
    $recentList.hide();
    $(".recommendation-keyword-recent-off").show();
    return;
  }

  $(".recommendation-keyword-recent-off").hide();

  if (this.getRecentKeywordSize() > 0 ) {
    var r_list=this.getSortedKeywords();
    var template = '';
    for(var i = 0; i < r_list.length; i++) {
      var r =  r_list[i];
      var r_keyword = r[0];
      var r_date = r[1];
      var d = new Date(r_date.substring(0, 4), r_date.substring(5, 7)-1, r_date.substring(8, 10), r_date.substring(11, 13), r_date.substring(14, 16), r_date.substring(17, 19));
      var offset = d.getTimezoneOffset();
      d.setTime(d.getTime() + offset * -1 * 60 * 1000);

      var year = d.getFullYear();
      var month = d.getMonth()+1;
      var date = d.getDate();

      month = month < 10 ? '0'+month : month;
      date = date < 10 ? '0'+date : date;

      template +=
          '<li class="keyword-recent-li">' +
          '  <a class="keyword-recent-trigger" onclick="SPgoSearch(\'' + r_keyword + '\', \'history\')">' +
          '    <strong class="keyword-recent-text">'+r_keyword+'</strong>' +
          '    <em class="keyword-recent-date">' +
          '      <span class="keyword-recent-date-year">'+year+' / </span>' +
          '      <span class="keyword-recent-date-month">'+month+' / </span>' +
          '      <span class="keyword-recent-date-day">'+date+'</span>' +
          '    </em>' +
          '  </a>' +
          '  <a class="keyword-recent-delete" onclick="SP.removeKeyword(\''+r_keyword+'\')">X</a>' +
          '</li>';
    }
    $recentList.html(template).show();
  } else {
    $recentList.html('<li class="recommendation-keyword-recent-noting">최근 검색어가 없습니다.<br>상품명 또는 브랜드명을 검색하세요.</li>').show();
  }
};
SB.prototype.toggleRecentSave= function() {
  if (this.getStoreData("st")) {
    $(".recommendation-keyword-recent-use-off").addClass('selected');
    $(".recommendation-keyword-recent-use-on").removeClass('selected');
    $(".recommendation-keyword-recent-menu-split").show();
    $(".recommendation-keyword-recent-delete").show();

  } else {
    $(".recommendation-keyword-recent-use-on").addClass('selected');
    $(".recommendation-keyword-recent-use-off").removeClass('selected');
    $(".recommendation-keyword-recent-menu-split").hide();
    $(".recommendation-keyword-recent-delete").hide();
  }
};
SB.prototype.getKeywordMap  = function () {
  return store.get('recentKeywords') || {};
};
SB.prototype.usingSave = function(st) {
  this.setStoreData("st", st);
  return this.getStoreData("st");
};
SB.prototype.storeKeyword = function(q) {
  if (!this.getStoreData("st")) return ;
  if (!!q) {
    var keywordMap = this.getKeywordMap();
    keywordMap[q] = {keyword: q, date: new Date()};
    var keys = this.getRecentKeywordSize();
    if (keys.length > 30) delete keywordMap[keys[0]];
    this.setStoreData("recentKeywords", keywordMap);
  }
};
SB.prototype.bindEventRecentKeyword  = function () {
  var obj = this;
  $('#recent-trigger').on("click", function () {
    var door = $('input[name="door"]').val();
    var qt = $('input[name="qt"]').val();
    if (obj.isNull(retry)) retry = "";

    analytics.track('Keyword Tab Clicks', {
      s_logType: 'clk',
      s_requestedPage: door,
      s_queryType: qt,
      s_clickType: 'SBH',
      s_clickAction: 'TAB',
      s_retry: retry});

    SB.prototype.appendRecentKeywordList();
    $("#realtime_keyword").hide();
    $("#recent_keyword").show();
    $(this).addClass('selected');
    $('#realtime-trigger').removeClass('selected');
  });

  $('.recommendation-keyword-recent-delete').click(function() {
    store.remove("recentKeywords");
    obj.appendRecentKeywordList();
  });
  $('.recommendation-keyword-recent-use-off').click(function() {
    obj.usingSave(false);
    // obj.toggleRecentSave();
    obj.appendRecentKeywordList();
  });
  $('.recommendation-keyword-recent-use-on').click(function() {
    obj.usingSave(true);
    obj.appendRecentKeywordList();
  });
};
SB.prototype.removeKeyword = function(keyword) {
  var keywordMap = this.getStoreData("recentKeywords");
  delete keywordMap[keyword];
  this.setStoreData("recentKeywords", keywordMap);
  this.appendRecentKeywordList();
  return this.getRecentKeywordSize();
};
SB.prototype.appendHiddenField = function() {
  var searchForm = document.forms['searchForm'];
  if (!this.isNull(this.params.ab))
    $('input[name=ab]').val(this.params.ab);

  if (!this.isNull(this.params.cache))
    $('input[name=cache]').val(this.params.cache);

  if (!this.isNull(this.params.purge))
    this.addHidden(searchForm, 'purge', this.params.purge);
};
SB.prototype.searchbarQuery = function() {
  var result = "";
  try {
    $.ajax({
      url: this.srp_url + "memebox/bar",
      dataType: 'JSONP',
      jsonpCallback: "sbqCallBack",
      success: function (data) {

        $search = $("#search");

        if (data.type == "search"){
          data.type = "searchKeyword";

          var clickSearchButtonLog = "SPsearchbarQueryLog('" + data.type + "', '" + data.title + "', '" + data.parameter + "', q.value);";
          $("#executeSearchButton").show();
          $("#landingPageButton").hide();
          $("input[name='recombar']").val("1");
          $("#executeSearchButton").attr("onclick", clickSearchButtonLog);

          $search.attr("placeholder", data.title);
          $search.attr("searchBarType", data.type);
          $search.attr("landingParameter", data.parameter);

        } else {
          if ($search.val() == "") {
            $("#executeSearchButton").hide();
            $("#landingPageButton").show();
          }

          var clickSearchButtonLog = "SPsearchbarQueryLog('" + data.type + "', '" + data.title + "', '" + data.parameter + "');";
          $("#landingPageButton").attr("href", data.landingURL);
          $("#search").attr("placeholder", data.title);
          $("#search").attr("searchBarType", data.type);
          $("#search").attr("landingParameter", data.parameter);

          $("input[name='recombar']").val("0");
          $("#landingPageButton").attr("onclick", clickSearchButtonLog);
        }

        if(data.type != null || data.type != undefined) {
          var impCode = "impBar" + data.type.substr(0, 1).toUpperCase() + data.type.substr(1);
          analytics.track('Searchbar Contents', {
            s_logType: impCode,
            s_barType: data.type});
        }
      }
    });

  } catch (e) {
    console.log(e);
  }

  return result;
};

SB.prototype.searchbarQueryLog = function(type, title, value, keyword) {
  var deal;
  var promotion;
  var searchKeyword;
  var label;

  if(keyword == "" || keyword == undefined) {
    switch (type) {
      case "deal":
        deal = value;
        label = title;
        break;
      case "promotion":
        promotion = value;
        label = title;
        break;
      default:
        searchKeyword = value;
        label = value;
        break;
    }

    analytics.track('ga',{
      s_logType: 'clk',
      s_barTitle: title,
      s_barType: type,
      s_promotionId: promotion,
      s_dealId: deal,
      s_keywords: searchKeyword,
      s_clickType: 'SBK',
      s_clickAction: 'CLK',
      category : '검색',
      action : '검색',
      label : label});
  } else {
    analytics.track('ga',{
      category : '검색',
      action : '검색',
      label : keyword});
  }
};

MEMEBOX.SearchBarLoad = function(surl, door) {
  var sb =  new SB (surl, door);
  SP = sb;
  sb.appendSearchbar();
  sb.bindEventSearchBox();
  sb.realTimeKeywordLoad();
  sb.bindEventRealTimeTabClick();
  sb.bindEventAutocomplete();
  sb.bindEventSearchbarClick();
  sb.appendRecentKeywordList();
  sb.appendHiddenField();

  sb.bindEventRecentKeyword();
  sb.bindEventCloseAutocomplete();
  sb.searchbarQuery();

};

function SPexecuteSearch() { return SB.prototype.executeSearch(); }
function SPgoSearch(q, qt) {SP.goSearch(q, qt);}
function SPOpenBestDeal(q, id, type) {SP.openBestDeal(q, id, type);}
function SPsearchbarQueryLog(type, title, value, keyword) { SP.searchbarQueryLog(type, title, value, keyword); }


