package com.example.giftgrower.controller;

import com.example.giftgrower.login.service.GoogleLoginManager;
import com.example.giftgrower.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/*
* RequestMapping은 URL을 컨트롤러의 메소드와 매핑할 때 사용하는 스프링프레임워크에서 제공하는 어노테이션 중 하나입니다.
* /user 값을 주어서 현재 컨트롤러에 메도스와 매핑되는 URL의 공통도상위 경로를 /user로 지정합니다.
* */

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Autowired
    private GoogleLoginManager googleLoginManager;

    @PostMapping("/userCheck")
    public ResponseEntity userCheck(@RequestBody String email) {
        Map returnMap = new HashMap();
        System.out.println("✿✿✿✿ /userCheck email ✿✿✿✿ "+email);
        int userCheck = -1;
        if (email == null || email.length() < 1) {
            userCheck = userService.userCheck(null);
        } else {
            userCheck = userService.userCheck(email);
        }
        System.out.println("✿✿✿✿ /userCheck userCheck ✿✿✿✿ "+userCheck);
        if(userCheck > 0){
            returnMap.put("userCheck", "success");
        } else {
            returnMap.put("userCheck", "fail");
        }
        return ResponseEntity.ok(returnMap);
    }
}