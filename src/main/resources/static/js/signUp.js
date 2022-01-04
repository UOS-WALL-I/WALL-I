let code = "";
let idCheck = false;
let codeCheck = false;
let passwordCheck = false;
let repeatCheck = false;

$('.input-email').on("propertychange change keyup paste input", function (){
    let email = $('.input-email').val();
    let data = {email : email};

    $.ajax({
        type : "post",
        url : "/members/emailCheck",
        data : data,
        success : function (result){
            if (result == "success") {
                $('.check-mail-re').text("사용 가능한 아이디 입니다.");
                $('.check-mail-re').css("color", "green");
                $('.check-email').attr("disabled", false);
                idCheck = true;
            } else {
                $('.check-mail-re').text("중복된 아이디 입니다.");
                $('.check-mail-re').css("color", "red");
            }
        }
    });
});

$('.check-email').click(function () {
    let email = $('.input-email').val();

    $.ajax({
        type : "GET",
        url : "/members/codeCheck?email=" + email,
        success: function (data) {
            $('.input-code').attr("disabled", false);
            $('.check-code').attr("disabled", false);
            code = data;
        }
    });
});

$('.check-code').click(function () {
    let inputCode = $('.input-code').val();
    if (inputCode == code) {
        $('.check-code-re').text("인증번호가 일치합니다.");
        $('.check-code-re').css("color", "green");
        $('.check-code').css("display", "none");
        codeCheck = true;
    } else {
        $('.check-code-re').text("인증번호가 일치하지 않습니다.");
        $('.check-code-re').css("color", "red");
    }
});

$('.input-pwd').blur(function () {
    let pwd = $('.input-pwd').val();
    if (pwd.length < 8 || pwd.length > 20) {
        $('.check-password-re').text("비밀번호의 길이는 8글자 이상 20글자 이하여야 합니다.");
        $('.check-password-re').css("color", "red");
    } else {
        $('.check-password-re').css("display", "none");
        passwordCheck = true;
    }
});

$('.input-rpwd').blur(function () {
    let pwd = $('.input-pwd').val();
    let rpwd = $('.input-rpwd').val();
    if (pwd == rpwd) {
        $('.check-repeat-re').text("비밀번호가 일치합니다.");
        $('.check-repeat-re').css("color", "green");
        repeatCheck = true;
    } else {
        $('.check-repeat-re').text("비밀번호가 같지 않습니다.");
        $('.check-repeat-re').css("color", "red");
    }
});

$('#signUp-button').click(function (){
    if (!idCheck || !codeCheck || !passwordCheck || !repeatCheck) {
        return false;
    }
});