package com.example.giftgrower.service;

import com.example.giftgrower.model.User;
import com.example.giftgrower.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
/*  @RequiredArgsConstructor는 lombok이 초기화 되지 않은 필드를 생성합니다. 이를 통해서 의존성 주입(Dependency Injection)을 할 수 있습니다. */
public class UserService {
    private final UserRepository userRepository;

    /* user 체크 */
    public int userCnt(String userId){
        return userRepository.countByUserId(userId);
    }

    /* pw 체크 */
    public User findByUserIdAndUserPw(String userId, String userPw) {
        return userRepository.findByUserIdAndUserPw(userId, userPw);
    }

    public int userCheck(String email){
        return userRepository.countByEmail(email);
    }

}