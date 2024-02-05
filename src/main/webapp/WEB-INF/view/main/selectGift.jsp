<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <script src="<c:url value="/jquery/external/jquery/jquery.js" />"></script>
    <script src="<c:url value="/jquery/jquery-ui.js" />"></script>
    <title>SELECT GIFT</title>
</head>
<body>
<div id="list">
</div>
<button type="button" class="btn" onclick="selectGift();">선택</button>

<script>
    $(document).ready(function () {
        giftList();
    });

    /* DB에 저장된 선물 목록 조회 */
    function giftList(){
        var html = "";
        $.ajax({
            url : "/giftList",
            contentType: "application/json; charset=UTF-8",
            dataType : "JSON",
            type : "GET" ,
            success : function(data){
                if(data.result === "success"){
                    if(data.data.length > 0){
                        for(var i=0; i<data.data.length; i++){
                            html += "<label for=\""+data.data[i].giftCd+"\">";
                            html += "<input type=\"radio\" name=\"giftList\" id=\""+data.data[i].giftCd+"\" value=\""+data.data[i].giftCd+"\"/>"+data.data[i].giftNm;
                            html += "</label><p>";
                        }
                    }

                }
                $("#list").html(html);
            }
        });
    }

    function selectGift(){
        var giftCd = "";
        var giftNm = "";
        var cnt = 0;
        $("input[name='giftList']:checked").each(function(e){
            cnt++;
            giftCd = $(this).val();
            giftNm = $("label[for='"+giftCd+"']").text();
        })
        if(cnt < 1){
            alert("선물을 선택해주세요!");
        } else {
            let data = {
                giftCd : giftCd,
                giftNm : giftNm
            };
            $.ajax({
                url : "/selectGift",
                contentType: "application/json; charset=UTF-8",
                dataType : "JSON",
                type : "POST" ,
                data : JSON.stringify(data),
                success : function(data){
                    alert(data.result);
                }
            });
        }
    }
</script>
</body>
</html>