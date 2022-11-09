$(document).ready(function () {
    const numRegExp = /^[0-9]+$/;
    const nameRegExp = /^[가-힣]{2,6}$/g;
    const SRDERegExp = /^([0-9]{6})-([0-9]{2})-([0-9]{6})$/;
    const YHRegExp = /^([0-9]{3})-([0-9]{6})-([0-9]{5})$/;
    const MSRegExp = /^([0-9]{3})-([0-9]{2})-([0-9]{6})$/;
    const HJRegExp = /^([0-9]{3})-([0-9]{3})-([0-9]{6})$/;
    let nameResult = false;
    let accountNumberResult = false;
    let moneyResult = false;


    var sum = 0;

    $("li").click(function () {
        $(".send").show();
        $("#sendSpan").text("보내기");
        $("#accountNumber").val($(this).find('.selectSendAccountNum').text());
        $("#bankName").val($(this).find('.selectSendBankName').text());
        $("#name").val($(this).find('.selectSendName').text());
        $("#sendName").text($(this).find('.selectSendName').text());

    });

    $("#selectLi").click(function () {
        $(".send").show();
        $("#sendSpan").text("보내기");
    });

    $("#name").keyup(function () {
        $("#sendName").text($("#name").val());
    });
    $("#money").keyup(function () {
        $("#resultmoney").text($("#money").val());
    });
    $("#accountNumber").blur(function () {
        if ($("#bankName").val() === "SR" || $("#bankName").val() === "DE") {
            if (!(SRDERegExp.test($("#accountNumber").val()))) {
                alert("은행계좌를 정확하게 입력해주세요. 양식:XXXXXX-XX-XXXXXX (-)필수입력");
            }
        }
        if ($("#bankName").val() === "YH") {
            if (!(YHRegExp.test($("#accountNumber").val()))) {
                alert("은행계좌를 정확하게 입력해주세요. 양식:XXX-XXXXXX-XXXXX (-)필수입력");
            }
        }
        if ($("#bankName").val() === "MS") {
            if (!(MSRegExp.test($("#accountNumber").val()))) {
                alert("은행계좌를 정확하게 입력해주세요. 양식:XXX-XX-XXXXXX (-)필수입력");
            }
        }
        if ($("#bankName").val() === "HJ") {
            if (!(HJRegExp.test($("#accountNumber").val()))) {
                alert("은행계좌를 정확하게 입력해주세요. 양식:XXX-XXX-XXXXXX (-)필수입력");
            }
        }
    });

    $("#moneybtn1").click(function () {
        var money = Number($('#money').val());
        sum += 10000;
        //console.log("10000");
        $("#money").val(sum + money);
        $("#resultmoney").text(sum + money);
    });
    $("#moneybtn2").click(function () {
        var money = Number($('#money').val());
        sum += 50000;
        $("#money").val(sum + money);
        $("#resultmoney").text(sum + money);
    });
    $("#moneybtn3").click(function () {
        var money = Number($('#money').val());
        sum += 100000;
        $("#money").val(sum + money);
        $("#resultmoney").text(sum + money);
    });
    $("#moneybtn4").click(function () {
        var money = Number($('#money').val());
        sum += 500000;
        $("#money").val(sum + money);
        $("#resultmoney").text(sum + money);
    });
    $("#moneybtn5").click(function () {
        var money = Number($('#money').val());
        sum += 1000000;
        $("#money").val(sum + money);
        $("#resultmoney").text(sum + money);
    });
    $("#remove").click(function () {
        sum = 0;
        $("#money").val('');
        $("#resultmoney").text('');

    });

    $("#ok").click(function () {
        var money = Number($('#money').val());
        if (money == 0 || money == '' || money == null) {
            $("#resultmoney").text('');
            // $(".resultmoney").text(money);
        } else {
            $("#resultmoney").text(money);
        }
        //$(".resultmoney").text(sum);
    })
    $("#sendBtn").click(function () {
        if ($("#sendSpan").text() === "펼치기") {
            $(".send").show();
            $("#sendSpan").text("보내기");
            return;
        }

        if ($("#accountNumber").val() === '' || $("#accountNumber").val() === null) {
            alert("계좌번호 입력은 필수입니다.");
            $("#accountNumber").focus();
            return;
        }
        if ($("#bankName").val() === '' || $("#bankName").val() === null) {
            alert("은행을 선택하세요");
            $("#bankName").focus();
            return;
        }

        if ($("#name").val() === '' || $("#name").val() === null) {
            alert("받는 사람 이름을 입력하세요");
            $("#name").focus();
            return;
        }

        if ($("#money").val() === '' || $("#money").val() === null) {
            alert("금액을 입력하세요");
            $("#money").focus();
            return;
        }

        if (Number($("#money").val()) > Number(total)) {
            alert("송금 가능한 금액이 아닙니다.");
            sum = 0;
            $("#money").val('');
            $("#resultmoney").text("");
            return;
        }

        alert("aa");

        if (SRDERegExp.test($("#accountNumber").val()) ||
            YHRegExp.test($("#accountNumber").val()) ||
            MSRegExp.test($("#accountNumber").val()) ||
            HJRegExp.test($("#accountNumber").val())) {
            alert("dddd");
            accountNumberResult = true;
        }
        alert("bb");

        if (!nameRegExp.test($("#name").val())) { //정규식에 맞지 않을 떄
            alert("이름을 정확하게 입력해주세요.");
            return;
        } else {
            nameResult = true;
        }
        alert("cc");


        if (!(numRegExp.test($("#money").val()))) { //정규식에 맞지 않을 떄
            alert("숫자만 입력하세요");
            return;
        } else {
            moneyResult = true;
        }

        if ($("#sendSpan").text() === "보내기") {
            if (nameResult && accountNumberResult && ($("#bankName").val().length) > 0 && moneyResult) {
                alert("송금이 완료되었습니다.");
                $("#sendBtn").attr("type", "submit");
            }
        }
    });
});