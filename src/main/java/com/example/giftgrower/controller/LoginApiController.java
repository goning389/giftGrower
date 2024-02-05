package com.example.giftgrower.controller;

import com.example.giftgrower.login.service.GoogleLoginManager;
import com.example.giftgrower.login.vo.LoginVO;
import com.example.giftgrower.model.SelectGift;
import com.example.giftgrower.model.User;
import com.example.giftgrower.service.GiftService;
import com.example.giftgrower.service.SelectGiftService;
import com.example.giftgrower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginApiController {

    @Autowired
    private GoogleLoginManager googleLoginManager;

    @Autowired
    private UserService userService;

    @Autowired
    private SelectGiftService selectGiftService;

    @PostMapping("/loginApi")
    public ResponseEntity loginApi(@RequestBody LoginVO vo) {
        Map returnMap = new HashMap();
        /* user 체크 */
        int userCnt = userService.userCnt(vo.getUserId());

        if(userCnt < 1){
            returnMap.put("result", "noUser");
        } else if(userCnt > 0) {
            /* pw 체크 */
            User user = userService.findByUserIdAndUserPw(vo.getUserId(), vo.getUserPw());
            if(user == null){
                returnMap.put("result", "wrongPw");
            } else {
                /* 현재 키우고 있는 선물 정보 */
                SelectGift selectGift = selectGiftService.findByUserId(vo.getUserId());
                System.out.println("✿✿✿✿ /loginApi gift ✿✿✿✿ "+ selectGift);
                if(selectGift.getGiftCd() == null){
                    returnMap.put("result", "success_/selectGiftPage");
                } else {
                    returnMap.put("result", "success_/mainPage");
                }


                // giftCd와 giftNm가 null이 아니면 값을 담아두고 main 화면으로 가야됨
                // giftCd와 giftNm가 null이면 location.href로 선물 셀렉하는 화면으로 이동
            }
        }


        return ResponseEntity.ok(returnMap);
    }

}