package com.example.giftgrower.model;

import com.example.giftgrower.login.vo.LoginVO;
import lombok.Data;

import javax.persistence.*;

/*@Getter
@Setter*/
@Data
@Entity
@Table(name = "user")
public class User {

    /* ✿✿✿✿ Entity 관련 설명 보아라. ✿✿✿✿
    * 먼저 데이터베이스에 저장하기 위해 유저가 정의한 클래스가 필요한데 그런 클래스를
       Entity라고 한다. Domain이라고 생각하면 된다.
       일반적으로 RDBMS에서 Table을 객체화 시킨 것으로 보면 된다.
       그래서 Table의 이름이나 컬럼들에 대한 정보를 가진다.

       @Id
       primary key를 가지는 변수를 선언하는 것을 뜻한다. @GeneratedValue 어노테이션은 해당 Id 값을
       어떻게 자동으로 생성할지 전략을 선택할 수 있다. 여기서 선택한 전략은 "AUTO"이다.

       @Table
       별도의 이름을 가진 데이터베이스 테이블과 매핑한다. 기본적으로 @Entity로 선언된 클래스의 이름은 실제
       데이터베이스의 테이블 명과 일치하는 것을 매핑한다. 따라서 @Entity의 클래스명과 데이터베이스의 테이블명이
       다를 경우에 @Table(name=" ")과 같은 형식을 사용해서 매핑이 가능하다.

       @Column
       @Column 선언이 꼭 필요한 것은 아니다. 하지만 @Column에서 지정한 변수명과 데이터베이스의 컬럼명을
       서로 다르게 주고 싶다면 @Column(name=" ") 같은 형식으로 작성하면 된다.
       그렇지 않은 경우에는 기본적으로 멤버 변수명과 일치하는 데이터베이스 컬럼을 매핑한다.
    * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private String userId;

    @Column(name="user_pw")
    private String userPw;

    @Column(name="email")
    private String email;

    @Column(name="idnt")
    private String idnt;

    public LoginVO toVO() {
        LoginVO vo = new LoginVO();
        vo.setUserId(this.userId);
        vo.setUserPw(this.userPw);
        vo.setEmail(this.email);
        vo.setIdnt(this.idnt);
        return vo;
    }

}