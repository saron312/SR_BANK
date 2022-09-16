$(document).ready(function () {
    ////조건 결과값 - false로 시작 input에 들어간 조건이 부합할 때만 true로 바뀜////
    let resid = false;
    let respw = false;
    let respw2 = false;
    let resname = false;
    let resphone = false;

    /////////////// 아이디 유효성 검사 (정규식, ajax 사용)////////////////
    $("#userid").blur(function () {
        let idError = $("#idError");
        idError.css("color", "#ff6289");

        ///ajax 조건식//
        $.ajax({
            url: '/idcheck',
            type: 'get',
            data: "userid=" + $("#userid").val(),
            dataType: 'text',
            success: function (check) {	// 통신 성공 시 "true" 혹은 "false" 반환
                if (check === "true") { // 아이디가 이미 존재함
                    //console.log(check);	// 확인용
                    idError.show().text("중복 아이디입니다.");
                    return resid=false;
                } else {	// "false" 일 경우 - 아이디가 존재하지 않을 경우
                    //console.log(check);	// 확인용
                    let regExp = /^[a-z0-9_-]{5,20}$/g; //아이디 정규식(영문 소문자, 숫자, 특수문자(-,_) 만 가능)
                    let id = $("#userid").val();

                    if (id === null || id === "") { //값이 없을 때
                        idError.show().text("필수 정보입니다.");
                        return resid=false;
                    } else if (!regExp.test(id)) { //정규식에 맞지 않을 때
                        idError.show().text("5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
                        return resid=false;
                    } else { //조건에 맞는 아이디일 때 true 반환
                        idError.css("color", "#77ab59");
                        idError.show().text("멋진 아이디네요!");
                        return resid = true;
                    }
                }
            },
            error: function () {
                console.log("아이디 체크 오류");
            }
        });
    });


    //////////// 비밀번호 정규식 검사 //////////////
    $("#password").blur(function () {

        //정규식 영어 대소문자,특수문자,숫자 필수입력, 8-16글자
        let regExp = /^(?=.*[a-zA-z0-9])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/g;

        let pw = $("#password").val();
        let pwError = $("#pwError");
        pwError.css("color", "#ff6289");

        //조건식//
        if (pw === null || pw === "") { //값 없을 때
            pwError.show().text("필수 정보입니다.");
            return respw = false;
        } else if (!regExp.test(pw)) { //정규식에 맞지 않을 때
            pwError.show().text("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
            return respw = false;
        } else { //조건에 맞을 때 true 반환
            pwError.css("color", "#77ab59");
            pwError.show().text("OK");
            return respw = true;
        }
    });

    ///////////// 비밀번호 재확인 (처음 입력한 비밀번호와 같을 때 true 반환) //////////////
    $("#password2").blur(function () {
        let pw = $("#password").val();
        let pw2 = $("#password2").val();
        let pwError2 = $("#pwError2");
        pwError2.css("color", "#ff6289");

        //조건식//
        if (pw2 == null || pw2 === "") { //값 없을 때
            pwError2.show().text("필수 정보입니다.");
            return respw2 = false;
        } else if (!(pw === pw2)) { //값이 같지 않을 때
            pwError2.show().text("비밀번호가 일치하지 않습니다.");
            return respw2 = false;
        } else { //일치할 때
            pwError2.css("color", "#77ab59");
            pwError2.show().text("비밀번호가 일치합니다.");
            return respw2 = true;
        }
    });

    ///////// 이름 입력 /////////
    $("#name").blur(function () {
        let regExp = /^[가-힣]{2,6}$/g; //정규식 조건 (한글, 2-6글자)
        let name = $("#name").val();
        let nameError = $("#nameError");
        nameError.css("color", "#ff6289");

        //조건식//
        if (name === null || name === "") { //값 없을 때
            nameError.show().text("필수 정보입니다.");
            return resname = false;
        } else if (!regExp.test(name)) { //정규식에 맞지 않을 떄
            nameError.show().text("한글을 사용하세요. (특수기호, 공백 사용 불가)");
            return resname = false;
        } else { //조건에 맞을 때 true 반환
            nameError.css("color", "#77ab59");
            nameError.show().text("OK");
            return resname = true;
        }
    });

    ////////////휴대폰 번호 ////////////
    $("#phone").blur(function () {
        let regExp = /^([01]{2})([0|1|6|7|8|9]{1})([0-9]{3,4})([0-9]{4})$/;  // -빼고 입력 01로 시작, 총 10-11글자
        let phone = $("#phone").val();
        let phoneError = $("#phoneError");
        phoneError.css("color", "#ff6289");

        //조건식//
        if (phone === null || phone === "") { //값 없을 때
            phoneError.show().text("필수 정보입니다.");
            return resname = false;
        } else if (!regExp.test(phone)) { //정규식에 맞지 않을 때
            phoneError.show().text("(-)제외하여 숫자만 정확히 입력해주세요.");
            return resname = false;
        } else { //조건에 맞을 때 true반환
            phoneError.css("color", "#77ab59");
            phoneError.show().text("OK");
            return resphone = true;
        }
    });


    //////////회원가입 버튼 입력한 input의 값이 조건에 부합할 때만 submit됨///////////
    $(".btn1").click(function () {
        //alert(resid+","+respw+","+respw2+","+resname+","+resphone); //확인용
        if (resid === true && respw === true && respw2 === true && resname === true && resphone === true) { //모두 true
            //if (resid === false && respw === false && respw2 === false && resname === false && resphone === false) { //모두 true
            $.ajax({
                url: "/join",
                type: "post",
                data: {userid : $("#userid").val(),
                       password : $("#password2").val(),
                       name : $("#name").val(),
                       phone: $("#phone").val()},
                // "userid=" + $("#userid").val() + "&password=" + $("#password2").val() +
                //     "&name=" + $("#name").val() + "&phone=" + $("#phone").val(),
                dataType: "html",
                success: function (data) {
                    alert("입력 성공"); //확인용
                    location.href = "/joinok"
                },
                error: function (xhr, status, error) {
                    alert(error);
                }
            });
        } else { //하나라도 false가 있을 때
            alert("내용을 다 채우세요."); //확인용
            if ($("#userid").val().length === 0) {
                $("#idError").show().text("필수 정보입니다");
            }
            if ($("#password").val().length === 0) {
                $("#pwError").show().text("필수 정보입니다.");
            }
            if ($("#password2").val().length === 0) {
                $("#pwError2").show().text("필수 정보입니다.");
            }
            if ($("#name").val().length === 0) {
                $("#nameError").show().text("필수 정보입니다.");
            }
            if ($("#phone").val().length === 0) {
                $("#phoneError").show().text("필수 정보입니다.");
            }
        }
    });

});