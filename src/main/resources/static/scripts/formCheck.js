var key = CryptoJS.enc.Utf8.parse("A-16-Byte-keyVal");
var iv = CryptoJS.enc.Utf8.parse("A-16-Byte-String");
function changeInfo(obj){
    var list  = $(obj).find("input,textarea");
    for (i = 0; i < list.length; i++) {
        var name = list[i].name;
        var value = list[i].value;
        $(list[i]).text(value);
        $(list[i]).val(encrypt(value));
    }
    return true;
}

function encrypt(context) {
    var encrypted = '';
    if (typeof(context) == 'string') {

    }else if(typeof(context) == 'object'){
        context = JSON.stringify(context);
    }
    var srcs = CryptoJS.enc.Utf8.parse(context);
    encrypted = CryptoJS.AES.encrypt(srcs, key, {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });
    return encrypted.toString();
}
