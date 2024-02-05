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

<input type="text" id="userId" name="userId" placeholder="아이디">
<input type="text" id="userPw" name="userPw" placeholder="비밀번호">
<button type="button" class="btn" onclick="findPwPage();">비밀번호 찾기</button>
<button type="button" class="btn" onclick="joinPage();">회원가입</button>
<button type="button" class="btn" onclick="login();">로그인</button>

<script>
    function login(){
        var userId = $("#userId").val();
        var userPw = $("#userPw").val();
        if(userId == null || userId == ""){
            alert("아이디를 입력해주세요.");
            $("#userId").focus();
            return false;
        } else if(userPw == null || userPw == ""){
            alert("비밀번호를 입력해주세요.");
            $("#userPw").focus();
            return false;
        } else {
            let data = {
                userId : userId,
                userPw : userPw
            };
            /*.ajax에서 호출한 url 에서 returnUrl을 가져오든지 아예 거기서 던지든지*/
            /*$.ajax({
                url : "/loginApi",
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
            console.log("param");
            console.log(data);
            /* ajax에서 로그인 처리 후 바로 mainPage 찾도록 해도 됨. 데이터 들고 가야되니까 */
            /* 해당 사용자가 현재 진행 중인 작물이 있는 지 봐야 main에 화면에 달라짐 */
            location.href = "/main";
        }
    }

    function joinPage(){
        location.href = "/join";
    }

    function findPwPage(){
        location.href = "/findPw";
    }
</script>
</body>
</html>