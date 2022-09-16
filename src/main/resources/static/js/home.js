function accountHyphen() {
    $("#logoHome").attr("href", "/home");
    $("#mediaMenu").attr("href", "/logout");
    $("#mediaMenu").text("로그아웃");
    $("#menuBar1").text(name);
    $("#menuBar1").attr("href", "/home");
    $("#menuBar2").attr("href", "/logout");
    $("#menuBar2").text("로그아웃");

    $("#DE").text($("#DE").text().replace(/(\d{6})(\d{2})(\d{6})/, '$1-$2-$3'));
    $("#HJ").text($("#HJ").text().replace(/(\d{3})(\d{3})(\d{6})/, '$1-$2-$3'));
    $("#YH").text($("#YH").text().replace(/(\d{3})(\d{6})(\d{5})/, '$1-$2-$3'));
    $("#MS").text($("#MS").text().replace(/(\d{3})(\d{2})(\d{6})/, '$1-$2-$3'));
    $("#SR").text($("#SR").text().replace(/(\d{6})(\d{2})(\d{6})/, '$1-$2-$3'));
}

$(document).ready(function () {
    accountHyphen();


    $(".btn1").click(function () {
        $.ajax({
            url: "/page",
            type: "get",
            data: {
                pageNum: ++firstPageNum,
                userid: id
            },
            dataType: "json",
            success: function (data) {
                //alert("입력 성공"); //확인용
                //alert(JSON.stringify(data)); //확인용
                var str = '';
                $.each(data, function (idx, val) {
                    /*alert(idx + " : " + val.bankname+", "+val.accountnumber);*/
                    str += '<li><a href="/account?accountnumber='+val.accountnumber+'"><strong>' + val.bankname + '은행</strong>';
                    str += '<em id="' + val.bankname + '">' + val.accountnumber + '</em></a></li>';
                });
                $("#test").append(str);
                accountHyphen();
                if (firstPageNum == lastPageNum - 1) {
                    $(".btn1").text("접기");
                }
                if (firstPageNum >= lastPageNum) {
                    $("li:gt(4)").remove();
                    $(".btn1").text("더보기");
                    return firstPageNum = 0;
                }
            },
            error: function (xhr, status, error) {
                alert(error);
            }
        });
    });
});