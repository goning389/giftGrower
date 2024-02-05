package com.example.giftgrower.model.request;

import lombok.Data;

/* Request 클래스는 lombok에서 지원하는 @Data 어노테이션을 사용했습니다.
@Data는 Getter, Setter, RequiredArgsConstructor, ToString, EqualsAndHashCode 어노테이션을 포함한 어노테이션입니다. */
@Data
public class UserRequest {
    private String userId;
    private String userPw;
    private String idnt;
    private String email;
}