package com.example.giftgrower.login.controller;

import com.example.giftgrower.login.service.GoogleLoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController2 {

    @Autowired
    private GoogleLoginManager googleLoginManager;

    @RequestMapping("/")
    public String login(HttpSession session, Model model) throws Exception {
        System.out.println("✿✿✿✿ [PROJECT giftGrower] LOGIN PAGE ✿✿✿✿");
        try {
            /* session에서 로그인 정보를 서치한 후 로그인된 상태라면 main.jsp 안 된 상태라면 login.jsp로 이동한다. */
            String googleLoginUrl = googleLoginManager.getAuthorizationUrl(session);
            System.out.println("✿✿✿✿ googleLoginUrl ✿✿✿✿ "+googleLoginUrl);
            model.addAttribute("googleLoginUrl",googleLoginUrl);
           /* model.addAttribute("clientId",clientId);
            model.addAttribute("clientSecret",clientSecret);
            model.addAttribute("redirectUri",redirectUri);
            model.addAttribute("state",state);*/
        } catch (Exception e){
            e.printStackTrace();
        }
        return "login/login";
    }

    /* Call '/mainPage' Test */
    @RequestMapping("/mainPage")
    public String mainPage() {
        /* 진행 중인 선물이 있는 지 체크
        *  없으면 selectGift.jsp, 있으면 main.jsp로 이동
        * */
        System.out.println("✿✿✿✿ mainPage ✿✿✿✿ ");
        return "main/main";
    }
    /* Call '/mainPage' Test */

    /* Call '/mainPage2' Test */
    @RequestMapping("/selectGiftPage")
    public String selectGiftPage() {
        System.out.println("✿✿✿✿ selectGiftPage ✿✿✿✿ ");
        return "main/selectGift";
    }
    /* Call '/mainPage2' Test */

    /* Call '/userCheck' Test */
    @RequestMapping("/userCheck")
    public String userCheck() {
        return "login/userCheck";
    }
    /* Call '/userCheck' Test */

    /* Call '/chatGpt' Test */
    @RequestMapping("/chatGpt")
    public String chatGpt() {
        return "main/chatGpt";
    }
    /* Call '/chatGpt' Test */
}