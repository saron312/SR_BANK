
$(document).ready(function () {
    $('.입금').css("color","#ff6289");
    $('.출금').css("color","#77ab59");

    $(".homeBtn").click(function () {
        $.ajax({
            url: "/transferPage",
            type: "get",
            data: {
                pageNum: ++firstPageNum,
                accountNumber: accountNumber
            },
            dataType: "json",
            success: function (data) {
                var str = '';
                $.each(data, function (idx, val) {
                    str += '<li><div><p>'+val.dwDate+'<div><em style="text-align:left; display: block; width:70px;">'+val.bankName+'은행</em>';
                    str += '<div style="display: inline-block; width: 40%;">' +
                        '<em>'+val.counterparty+'</em></div><em style="padding:0;">'+val.name+'</em></div></div>';
                    str += '<div><div style="display: inline-block; width:79%;"><div style="text-align: right; font-size:1.4em">' +
                        '<em style="padding:0;">'+val.dw+'</em></div>';
                    str += '<div style="text-align: right"><em style="padding:0;">잔액</em></div></div>';
                    str += '<div style="display:inline-block; text-align:right; width:20%;">' +
                        '<div><em id="money" style="padding:0 25px 0 0; font-size:1.4em; ';
                    if(val.dw === '입금'){
                        str += 'color:#ff6289;"'
                    }else{
                        str += 'color:#77ab59;"'
                    }
                    str += '>'+val.money+'</em></div></em>';
                    str += '<div><em style="padding:0 25px 0 0;">'+val.total+'</em></div></div></div>';
                    str += '</li>';
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
                alert("연결실패");
            }
        });
    });

});
