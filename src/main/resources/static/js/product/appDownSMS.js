var inputCodeValidate = function inputCodeValidate(mobileCode) {
    var code = mobileCode.value;
    var codeLength = code.length;
    var codePiece = code.charAt(0);
    var pattern = /[\-]/gi;

    for (var i = 0; i < codeLength; i++) {
        codePiece = code.charAt(i);

        if (codePiece >= '0' && codePiece <= '9') {
            continue;
        } else if(codePiece == '-') {
            mobileCode.value = code.replace(pattern, '');
            continue;
        } else {
            alert('숫자만 입력이 가능합니다.');
            mobileCode.value="";
            mobileCode.focus();
            return false;
        }
    }
    return true;
};

var sendAppDownSMS = function(params, cb) {

    var mobile = params.phone;

    if ( ! mobile) {
        alert('휴대폰 번호를 입력해주세요.');
        return cb(false);
    } else {
        var apiUrl = '/api/v4/extra/appDownSms';
        var apiData = { mobile : mobile , msg : params.msg };
        var inspectMobile = mobileValidate(mobile);

        if (inspectMobile) {
            $.ajax({
                type: 'post',
                url: apiUrl,
                data: apiData,
                cache: false
            }).done(function(jqXHR) {
                if ( !!jqXHR.msg ) {
                    alert(jqXHR.msg);
                }
                return cb(true);
            }).fail(function(jqXHR) {
                try {
                    var failMsg = JSON.parse(jqXHR.responseText).msg;
                    alert(failMsg);
                    return cb(false);
                } catch (e) {
                    return cb(false);
                }
            }).always(function() {

            });
        } else {
            alert('올바른 휴대폰 번호를 입력해주세요.');
            return cb(false);
        }
    }
};

var mobileValidate = function mobileValidate(mobile) {
    var pattern = /^01[0|1|6|7|8|9]+[0-9]{3,4}[0-9]{4}$/;
    var mobileValidate = pattern.test(mobile);

    return mobileValidate;
};

