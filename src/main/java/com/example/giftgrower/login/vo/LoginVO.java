package com.example.giftgrower.login.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;

@Data
/*@Getter
@Setter*/
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 *	로그인정보
 */
public class LoginVO implements Serializable {

    /*  USERINFO */

    @Column(name = "user_id")
    private String userId;

    @Column(name = "idnt")
    private String idnt;

    @Column(name = "email")
    private String email;

    @Column(name = "user_pw")
    private String userPw;
}

