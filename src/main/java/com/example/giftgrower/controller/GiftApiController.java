package com.example.giftgrower.controller;

import com.example.giftgrower.login.vo.LoginVO;
import com.example.giftgrower.model.Gift;
import com.example.giftgrower.model.SelectGift;
import com.example.giftgrower.model.User;
import com.example.giftgrower.service.GiftService;
import com.example.giftgrower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GiftApiController {

    @Autowired
    private GiftService giftService;

    /*
    * 선물 등록/수정/삭제 페이지 필요
    * -> 관리자 페이지
    * 등록할 때 gift_cd는 랜덤함수 사용 예정
    * */
    @GetMapping("/giftList")
    public ResponseEntity giftList() {
        Map returnMap = new HashMap();
        List<Gift> data = giftService.findAll();
        returnMap.put("data", data);
        returnMap.put("result", "success");
        return ResponseEntity.ok(returnMap);
    }

    @PostMapping("/selectGift")
    public ResponseEntity selectGift() {
        Map returnMap = new HashMap();
        // 로그인 정보 담겨있는 놈 > 아이디 꺼내오기
        // 나머지는 파라미터로 받아왔으니... 둘이 합쳐 selectGift table update
        returnMap.put("result", "success");
        return ResponseEntity.ok(returnMap);
    }

}