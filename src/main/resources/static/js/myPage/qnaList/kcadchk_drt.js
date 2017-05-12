
//  쿠키시간 조절옵션 : 기본인 720
 leftTime="24"

if(_setlefttime!=""){
	leftTime = _setlefttime;
}


//광고주요청에 따른 쿠키시간 변경작업 추후 옵션작업영역
function kcmaster_drt(){
		var _JEx1 ="KCVAR1";
		var _JEx2 ="KCVAR2";
		var _JEx3 ="KCVAR3";
		var _JEx4 ="KCVAR4";
		var _JEx5 ="KCVAR5";
		var _JEx6 ="KCVAR6";
		var _JEx7 ="KCVAR7";
		var _JEx8 ="NVAR";
		var _JEx9 ="KE";
		var _JL = document.location.search.substr(1);	// 쿼리 스트링값을 전달받는다

		 /// URL뽑기
		if(_JL.indexOf("http://")>0){
			_MURL = _JL.substring(_JL.indexOf("url")+4,_JL.indexOf("&KCVAR1"));
		}else{
			_MURL = RURL.substring(RURL.indexOf("http://"),RURL.indexOf("KCVAR1")-1);
		}
		_MURL_SEND = encodeURIComponent(_MURL);

		var _JQS = {}; // query array
		_JL.replace(/([^=]+)=([^&]*)(&|$)/g, function(){
			_JQS[arguments[1]] = arguments[2];
			return arguments[0];
		});

		// 네이버 추적코드
		var NV = "";
		if(_JQS[_JEx8]==undefined){
			NV = "";
		}else{
			NV = _JQS[_JEx8];
		}
		// 모바일 체크 여부
		var _MBCHK = "M";
		if(_JQS[_JEx7]==undefined){
			_MBCHK = "M" + mobilenum;
		}else{
			_MBCHK = _JQS[_JEx7] + mobilenum;
		}

		// 모바일 경로 가져오기
	//	var _MBURL = "";
		if(_JQS[_JEx6] != undefined){
			_MBURL = _JL.substring(_JL.indexOf("KCVAR6")+7,_JL.indexOf("&KCVAR7"));
			_MBURL_SEND = encodeURIComponent(_MBURL);
		}else{
			_MBURL = "";
			_MBURL_SEND = "";
		}

	// 실제 이동URL 구하기
		if(mobilenum > 0){
			if(_MBURL == ""){
				_moveURL = _MURL;
			}else{
				_moveURL = _MBURL;
			}
		}else{
			_moveURL = _MURL;
		}

		if(_JQS[_JEx1]== undefined){
			_JQSC = "";
		}else{
			_JQSC=_JQS[_JEx1] + "|" + _JQS[_JEx2] + "|" + _JQS[_JEx3] + "|" + _JQS[_JEx4] + "|" + _JQS[_JEx5] + "|" + NV + "|" + _MURL_SEND + "|" + _JQS[_JEx7] + "|" + _MBURL_SEND + "|" + _MBCHK ;
		}

		if(_JQSC!=""){
			_KCScookie('adkosmisCookieGet_drt',encodeURIComponent(_JQSC),leftTime);
		}else {
			if(_KCGcookie('adkosmisCookieGet_drt')!=undefined){
				_JQSC=decodeURI(decodeURIComponent(_KCGcookie('adkosmisCookieGet_drt')));
			}else{
			}
		}

		return _JQSC;
}


var _JQSC_drt=kcmaster_drt();
var _kcadtypecode;
var _kcadconvertdata1;
var _kcadconvertdata2;
var _kcadconvertdata3;
var _kcadconvertdata4;
var _kcadconvertdata5;
var _kcadconvertdata6;

//alert(document.cookie)

if(_JQSC_drt.indexOf("LOAD") <0 || _kcadtypecode != undefined){
	if(_JQSC_drt!="|LOAD"){
		if(_kcadtypecode==undefined){_kcadtypecode=0;}
		if(_kcadconvertdata1 == undefined){_kcadconvertdata1 = "";}
		if(_kcadconvertdata2 == undefined){_kcadconvertdata2 = "";}
		if(_kcadconvertdata3 == undefined){_kcadconvertdata3 = "";}
		if(_kcadconvertdata4 == undefined){_kcadconvertdata4 = "";}
		if(_kcadconvertdata5 == undefined){_kcadconvertdata5 = "";}
		if(_kcadconvertdata6 == undefined){_kcadconvertdata6 = "";}

		_kcadconvertdata1 = encodeURIComponent(_kcadconvertdata1);
		_kcadconvertdata2 = encodeURIComponent(_kcadconvertdata2);
		_kcadconvertdata3 = encodeURIComponent(_kcadconvertdata3);
		_kcadconvertdata4 = encodeURIComponent(_kcadconvertdata4);
		_kcadconvertdata5 = encodeURIComponent(_kcadconvertdata5);
		_kcadconvertdata6 = encodeURIComponent(_kcadconvertdata6);

		//var txt   = adUrl+'/getkcscriptdata?arrAdCode='+_JQSC + "&AdType=" + _kcadtypecode + "&AdCvtData1=" + _kcadconvertdata1 + "&AdCvtData2=" + _kcadconvertdata2 + "&AdCvtData3=" + _kcadconvertdata3 + "&AdCvtData4=" + _kcadconvertdata4 + "&AdCvtData5=" + _kcadconvertdata5 + "&AdCvtData6=" + _kcadconvertdata6;
		//document.frm.chkurl.value = txt;

		if(_kcadtypecode!='0'){
			var im2=new Image();
			im2.onload=function() { return; }
			im2.src= adUrl+'/getkcscriptdata?CHK=D&arrAdCode='+_JQSC + "&AdType=" + _kcadtypecode + "&AdCvtData1=" + _kcadconvertdata1 + "&AdCvtData2=" + _kcadconvertdata2 + "&AdCvtData3=" + _kcadconvertdata3 + "&AdCvtData4=" + _kcadconvertdata4 + "&AdCvtData5=" + _kcadconvertdata5 + "&AdCvtData6=" + _kcadconvertdata6;
		}

		if(_JQSC_drt.indexOf("LOAD")< 0){
			_JQSC_drt = _JQSC_drt + "|LOAD"
			_KCScookie('adkosmisCookieGet_drt',encodeURIComponent(_JQSC_drt),leftTime);
		}
	}
}
