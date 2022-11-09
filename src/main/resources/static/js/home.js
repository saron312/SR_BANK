

$(document).ready(function () {

    $(".sendAccount").click(function () {
        $("#accountNumber").val($(this).children('em').text());
        $("#bankName").val($(this).children('strong').text());
        $("#accountForm").attr("action", "/account");
        $("#accountForm").attr("method", "post");
        $("#accountForm").submit();
    });

    $(".homeBtn").click(function () {
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
                    str += '<li><a href=""><strong class="bankName">' + val.bankName + '은행</strong>';
                    str += '<em class="' + val.bankName + '">' + val.accountNumber + '</em></a></li>';
                });
                $("#test").append(str);
                if (firstPageNum == lastPageNum - 1) {
                    $(".homeBtn").text("접기");
                }
                if (firstPageNum >= lastPageNum) {
                    $("li:gt(4)").remove();
                    $(".homeBtn").text("더보기");
                    return firstPageNum = 0;
                }
            },
            error: function (xhr, status, error) {
                alert(error);
            }
        });
    });
});
