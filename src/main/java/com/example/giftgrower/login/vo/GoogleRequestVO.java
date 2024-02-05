package com.example.giftgrower.login.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GoogleRequestVO {
    private String clientId; // 애플리케이션의 클라이언트 ID
    private String redirectUri; // 구글 로그인 후 redirect 위치
    private String clientSecret; // 클라이언트 보안 비밀
    private String responseType; // Google OAuth 2.0 앤드포인트의 인증코드 반환여부
    private String scope; // OAuth 동의 범위
    private String code;
    private String accessType; // 사용자가 브라우저에 없을 때 애플리케이션이 액세스 토큰을 새로 고칠 수 있는 지
    private String grantType;
    private String state;
    private String includeGrantedScopes; // 애플리케이션이 컨텍스트에서 추가 범위에 대한 액세스를 요청하기 위해 추가 권한 부여를 사용
    private String loginHint; // 애플리케이션이 인증하려는 사용자를 알고 있는 경우 이 매개변수를 사용하여 Google 인증 서버에 힌트를 제공
    private String prompt; // default: 처음으로 액세스를 요청할 때만 사용자에게 메시지가 표시
}
