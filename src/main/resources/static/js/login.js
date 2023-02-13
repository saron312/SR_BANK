$(document).ready(function () {
    let resid = false;
    let respw = false;
    $("#userId").focus(function (){
        $("#loginError").hide();
    });
    $("#password").focus(function (){
        $("#loginError").hide();
    });

    /////////////// 아이디 유효성 검사 (정규식, ajax 사용)////////////////
    $("#userId").blur(function () {
        let idError = $("#idError");
        ///ajax 조건식//
        // $.ajax({
        //     url: '/idCheck',
        //     type: 'get',
        //     data: {userId : $("#userId").val()},
        //     dataType: 'text',
        //     success: function (check) {	// 통신 성공 시 "true" 혹은 "false" 반환
        //         if (check == "true") { // 아이디가 이미 존재함
        //             //console.log(check);	// 확인용
        //             idError.hide();
        //             resid = true;
        //         } else {// "false" 일 경우 - 아이디가 존재하지 않을 경우
                    //console.log(check);	// 확인용
                    let id = $("#userId").val();
                    if (id === null || id === '') { //값이 없을 때
                        idError.show().text("아이디를 입력해주세요.");
                        return resid = false;
                    } else{
                        return resid = true;
                    }
                // }
            // },
            // error: function () {
            //     console.log("아이디 체크 오류");
            // }
        // });
    });


    $("#password").blur(function () {
        //정규식 영어 대소문자,특수문자,숫자 필수입력, 8-16글자
        let regExp = /^(?=.*[a-zA-z0-9])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/g;

        let pw = $("#password").val();
        let pwError = $("#pwError");

        //조건식//
        if (pw === null || pw === "") { //값 없을 때
            pwError.show().text("비밀번호를 입력해주세요.");
            return respw = false;
        } else if (!regExp.test(pw)) { //정규식에 맞지 않을 때
            pwError.show().text("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
            return respw = false;
        } else {
            pwError.hide();
            return respw = true;
        }
    });

    //////////로그인 버튼 입력한 input의 값이 조건에 부합할 때만 submit됨///////////
    $("#loginSubmit").submit(function () {
        if(resid === true && respw === true){
            return true;
        }else{
            if ($("#userId").val() === null || $("#userId").val() === "") {
                $("#idError").show().text("아이디를 입력해주세요");
            }
            if ($("#password").val() === null || $("#password").val() === "") {
                $("#pwError").show().text("비밀번호를 입력해주세요");
            }
            return false;
        }

    });
});