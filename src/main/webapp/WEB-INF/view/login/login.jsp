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
<input type="password" id="userPw" name="userPw" placeholder="비밀번호">
<button type="button" class="btn" onclick="login();">로그인</button><p>
<button type="button" class="btn" onclick="googleLogin();">구글로그인</button>
<button type="button" class="btn" onclick="joinPage();">회원가입</button>
<button type="button" class="btn" onclick="findPwPage();">비밀번호 찾기</button>

<script>
    $(document).ready(function(){
        //JPA_test();
        alert("login.jsp");
    });

    function JPA_test(){
        var data = "qweiopzxc1324";
        console.log(data);
        $.ajax({
            url : "/user/userCheck",
            contentType: "application/json; charset=UTF-8",
            dataType : "JSON",
            type : "POST" ,
            data : data,
            success : function (data){
                console.log("↓ JPA_test result ↓");
                console.log(data.userCheck);
                if(data.userCheck == "success"){
                    //location.href = "/main";
                    alert("로그인 성공");
                } else {
                   alert("해당 계정은 존재하지 않습니다.");
                }
            }
        });
    }

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
            $.ajax({
                url : "/loginApi",
                contentType: "application/json; charset=UTF-8",
                dataType : "JSON",
                type : "POST" ,
                data : JSON.stringify(data),
                success : function(data){
                    if(data.result === "noUser"){
                        alert("사용자 정보가 존재하지 않습니다.");
                        $("#userId").val("");
                        $("#userPw").val("");
                        return false;
                    } else if(data.result === "wrongPw"){
                        alert("비밀번호가 틀렸습니다.");
                        $("#userPw").val("");
                        return false;
                    } else if (data.result.indexOf("success") != -1){
                        alert("성공적으로 로그인 되었습니다.");
                        var urlArr = data.result.split("_");
                        var url = urlArr[1];
                        if(url === "/selectGiftPage"){
                            alert("현재 키우는 선물이 없습니다. 선물을 골라주세요!");
                        }
                        location.href = url;
                    }
                }
            });
            /* ajax에서 로그인 처리 후 바로 mainPage 찾도록 해도 됨. 데이터 들고 가야되니까 */
            /* 해당 사용자가 현재 진행 중인 작물이 있는 지 봐야 main에 화면에 달라짐 */
        }
    }

    function googleLogin(){
        var curX = window.screenLeft;
        var curY = window.screenTop;
        var popupX = curX + (document.body.offsetWidth / 2) - (480 / 2);
        var popupY = curY + (window.screen.height / 2) - (800 / 2);

        var GOOGLE_window = window.open('${googleLoginUrl}', 'auth_popup', 'width=480, height=800, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=' + popupX + ', top=' + popupY);

        if(GOOGLE_window == null){
            alert(" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \n    화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
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