package com.example.giftgrower.login.controller;

import com.example.giftgrower.login.service.GoogleLoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginApiController2 {

    @Autowired
    private GoogleLoginManager googleLoginManager;

    /*@Autowired
    private LoginService loginService;*/

    /*@PostMapping("/loginApi")
    *//*public ResponseEntity loginApi(@RequestBody LoginVO vo) {*//*
    public ResponseEntity loginApi(String userId, String userPw) {
        Map returnMap = new HashMap();
        *//*System.out.println("✿✿✿✿ /loginApi email ✿✿✿✿ "+vo.getUserId());
        System.out.println("✿✿✿✿ /loginApi email ✿✿✿✿ "+vo.getUserPw());*//*
        System.out.println("✿✿✿✿ /loginApi email ✿✿✿✿ "+userId);
        System.out.println("✿✿✿✿ /loginApi email ✿✿✿✿ "+userPw);
        returnMap.put("result", "success");
        return ResponseEntity.ok(returnMap);
    }*/

    /*@RequestMapping(value = "/googleLogin")
    public String googleLogin(HttpServletRequest request, ModelMap model, String code, String state) throws Exception {
        System.out.println("#### [PROJECT giftGrower] googleLogin ####");
        final HttpSession session = request.getSession();
        // 전달 받은 code를 사용해서 access_token 받기
        final Map tokenMap = googleLoginManager.getAccessToken(code, state);

        String accessToken = "";
        String refreshToken = "";
        if (tokenMap != null) {
            accessToken = (String) tokenMap.get("access_token");
            refreshToken = (String) tokenMap.get("refresh_token");

            session.setAttribute("googleAT", accessToken);
            session.setAttribute("googleRT", refreshToken);

            System.out.println("########## googleLogin accessToken : " + accessToken);
            System.out.println("########## googleLogin refreshToken : " + refreshToken);
        }

        try {
            //return받은 access_token으로 사용자 정보 가져오기
            final HashMap<String, Object> userInfo = googleLoginManager.getUserInfo(accessToken);

            //구글에서 받은 정보로 DB에 해당 사용자가 있는지 확인 후 없으면 회원가입 페이지로, 있으면 sns 로그인 처리해준다.
            if(userInfo != null) {	//사용자 정보가 존재하면 SNS로그인 처리해주도록 redirect
                System.out.println("########## googleLogin userInfo != null");
                final String idnt = (String)userInfo.get("id");
                final String email = (String)userInfo.get("email");
                System.out.println("###### googleLogin idnt : " + idnt);
                System.out.println("###### googleLogin email : " + email);
                System.out.println("###### googleLogin userInfo : " + userInfo.toString());

                *//*final Map<String, Object> result = loginService.processSnsLogin("google",idnt, email);*//*

                *//*session.setAttribute("preLoginInfo", result.get("loginInfo"));

                model.addAttribute("userId",  ((LoginInfoVO)result.get("loginInfo")).getUserId());
                model.addAttribute("sltScrtNo", ((LoginInfoVO)result.get("loginInfo")).getSltScrtNo());
                model.addAttribute("loginGbn", "google");
                model.addAttribute("snsIdnt", idnt);*//*
            } else {	// 인증 실패
                model.addAttribute("resultMessage", "구글 인증이 정상적으로 진행되지 못했습니다.");
            }
        } catch (Exception e) {
            model.addAttribute("resultMessage", e.getMessage());
        }
        return "login/googleLogin";
    }*/
}