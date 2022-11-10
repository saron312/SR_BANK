$(document).ready(function () {
    $("#createBtn").click(function () {
        ///ajax 조건식//
        $.ajax({
            url: '/createAccount',
            type: 'get',
            data: {userId: $("#userId").text()},
            dataType: 'text',
            success: function (data) {
                alert(data);
                window.location="/user/info"
            },
            error: function () {
                alert("계좌 생성 실패");
            }
        });
    });
    $("#phoneNum").text($("#phoneNum").text().replace(/^(\d{3})(\d{4})(\d{4})$/,"$1-$2-$3"));
});