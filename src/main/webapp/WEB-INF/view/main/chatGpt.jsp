<%@ page import="com.wezlesoft.portal.common.vo.LoginInfo" %>
<%@ page import="com.wezlesoft.portal.util.CommonUtil" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2023-09-12
  Time: 오후 8:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>chatGPT API</title>
    <script src="/jquery/jquery.min.js"></script>
    <style>
        /* page-loading */
        #loading {
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            position: fixed;
            display: block;
            opacity: 0.6;
            background: #e4e4e4;
            z-index: 99;
            text-align: center;
        }

        #loading>img {
            position: absolute;
            top: 40%;
            left: 45%;
            z-index: 100;
        }

        #loading>p {
            position: absolute;
            top: 57%;
            left: 43%;
            z-index: 101;
        }
    </style>
</head>

<body>
<h1>chatGPT API</h1>
<div>무엇이든 질문해보세요 !!</div>

<input type="text" id="keywords" name="keywords" required />
<button onclick="chatGPT()">입력</button>
<div id="result"></div>

<div id="loading">
    <img src="/images/loading.gif">
</div>
<script>

    let returnMessage = "";
    let allMessage = "";
    var chatGPT_userId = "${loginInfo.userId}";
    $(document).ready(function () {
        $('#loading').hide();
        if(chatGPT_userId != sessionStorage.getItem('chatGPT_userId')){
            sessionStorage.removeItem('returnMessage');
            sessionStorage.removeItem('allMessage');
        } else {
            let pre = document.createElement('pre');
            allMessage = sessionStorage.getItem('allMessage');
            var sessionAllContent = sessionStorage.getItem('allMessage').split("$$$");
            for(var i=0; i<sessionAllContent.length-1; i++){
                var sessionContent = sessionAllContent[i].split("@@@");
                var sessionContent_Q = sessionContent[0];
                var sessionContent_A = sessionContent[1];
                pre.innerHTML += "[Question💡]" + sessionContent_Q + "\n\n" + sessionContent_A + "\n\n"
            }
            result.appendChild(pre);
        }
        sessionStorage.setItem("chatGPT_userId", chatGPT_userId);
    });

    function chatGPT() {
        const api_key = "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"  // <- API KEY 입력 .
        const keywords = document.getElementById('keywords').value
        $('#loading').show();
        if(sessionStorage.getItem('returnMessage') == null){
            returnMessage = "";
        } else {
            returnMessage = sessionStorage.getItem('returnMessage');
        }
        const messages = [
            { role: 'system', content: 'You are a helpful assistant.' },
            { role: 'assistant', content: returnMessage},
            { role: 'user', content: keywords + '에 대하여 최대한 도움이 되는 답변을 해줘.' },
        ]


        const data = {
            model: 'gpt-3.5-turbo',
            temperature: 0.7,
            n: 1,
            messages: messages,
        }

        $.ajax({
            url: "https://api.openai.com/v1/chat/completions",
            method: 'POST',
            headers: {
                Authorization: "Bearer " + api_key,
                'Content-Type': 'application/json',
            },
            data: JSON.stringify(data),
        }).then(function (response) {
            $('#loading').hide();
            console.log(response);
            let result = document.getElementById('result');
            let pre = document.createElement('pre');

            returnMessage = response.choices[0].message.content;
            allMessage += keywords+"@@@"+returnMessage+"$$$";
            setSession("returnMessage", returnMessage);
            setSession("allMessage", allMessage);

            pre.innerHTML = "[Question💡]" + keywords + "\n\n" + returnMessage
            result.appendChild(pre);
            document.getElementById('keywords').value = ''
        });
    }

    function setSession(key, data){
        sessionStorage.setItem(""+key+"", data);
    }
</script>
</body>

</html>
