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
    <title>LOGIN</title>
</head>
<body>

<script>
    $(document).ready(function(){
        userCheck();
    });

    function userCheck(){
        alert("###");
        // 로그인한 구글 계정이 디비에 존재하는지 확인한다.
        // 없다면 회원가입 alert
        // 있다면 mainPage로 이동. 현재는 테스트임으로 무조건 mainPage로 이동시킨다.
        // 현재창 죽이고 부모창으로 이동하는 거임
        /*$.ajax({
            url : "/userCheck/"+,
            contentType: "application/json; charset=UTF-8",
            dataType : "JSON",
            type : "POST" ,
            data : JSON.stringify(data),
            success : function(data){
                if(data.errorMsg == null){
                    location.href = "/movie/listPage";
                } else {
                    alert(data.errorMsg);
                    $("#userId").val("");
                    $("#userPw").val("");
                    return false;
                }
            }
        });*/
        opener.document.location.href="/mainPage";
        self.close();

    }
</script>
</body>
</html>