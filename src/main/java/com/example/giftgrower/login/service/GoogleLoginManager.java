/**
 *
 */
package com.example.giftgrower.login.service;

import com.example.giftgrower.util.HttpConnectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 *	구글 소셜 로그인
 */
@Component("googleLoginManager")
public class GoogleLoginManager
{
	protected static final Log log = LogFactory.getLog(GoogleLoginManager.class);

	/** 구글 CLIENT_ID */
	@Value("${google.clientId}")
	private String CLIENT_ID;

	/** 구글 CLIENT_SECRET */
	@Value("${google.clientSecret}")
	private String CLIENT_SECRET;

	/** 구글 로그인 REDIRECT_URI */
	@Value("${google.redirectUri}")
	private String REDIRECT_URI;

	/** 구글 연동 REDIRECT_URI */
	@Value("${google.connectRedirectUri}")
	private String CONNECT_REDIRECT_URI;

	/** */
	public GoogleLoginManager() {
	}

	/**
	 * 구글 로그인 URL
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getAuthorizationUrl(HttpSession session) throws UnsupportedEncodingException {
		return getAuthorizationUrl(session, null);
	}

	/**
	 * 구글 로그인 URL
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getAuthorizationUrl(HttpSession session, String gbn) throws UnsupportedEncodingException {
		final String clientId = CLIENT_ID;//애플리케이션 클라이언트 아이디값";

		String redirectURI = URLEncoder.encode(REDIRECT_URI, "UTF-8");
		if ("connect".equals(gbn)) {
			redirectURI = URLEncoder.encode(CONNECT_REDIRECT_URI, "UTF-8");
		}

		final SecureRandom random = new SecureRandom();
		final String state = new BigInteger(130, random).toString();

		String apiURL = "https://accounts.google.com/o/oauth2/v2/auth?response_type=code";
		apiURL += "&client_id=" + clientId;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&scope=email%20profile";
		apiURL += "&access_type=offline";
		apiURL += "&state=" + state;
		apiURL += "&prompt=select_account";

		session.setAttribute("state", state);

		return apiURL;
	}

	/**
	 * 구글 로그인 access_token 리턴
	 * @param code
	 * @param state
	 * @return
	 */
	public Map getAccessToken(String code, String state) throws Exception {
		return getAccessToken(code, state, null);
	}

	/**
	 * 구글 로그인 access_token 리턴
	 * @param code
	 * @param state
	 * @return
	 */
	public Map getAccessToken(String code, String state, String gbn) throws Exception {

		String redirectURI = URLEncoder.encode(REDIRECT_URI, "UTF-8");
		if ("connect".equals(gbn)) {
			redirectURI = URLEncoder.encode(CONNECT_REDIRECT_URI, "UTF-8");
		}

		final String reqURL = "https://oauth2.googleapis.com/token";

		final Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("grant_type", "authorization_code");
		requestMap.put("client_id", CLIENT_ID);  //본인이 발급받은 key
		requestMap.put("client_secret", CLIENT_SECRET);	    //본인이 발급반은 client secret
		requestMap.put("redirect_uri", redirectURI);	    // 본인이 설정해 놓은 경로
		requestMap.put("code", code);
		requestMap.put("state", state);

		final String rslt = HttpConnectionUtil.postRequest(reqURL, "", requestMap);

		Map tokenMap = null;
		if(rslt != null && !rslt.equals("") ) {
			final ObjectMapper mapper = new ObjectMapper();
			tokenMap = mapper.readValue(rslt, Map.class);
		}

		return tokenMap;
	}

	/**
	 * 구글 사용자 정보 추출
	 * @param accessToken
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HashMap<String, Object> getUserInfo(String accessToken) throws Exception {
		//요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		final HashMap<String, Object> userInfo = new HashMap<>();

		final String reqURL = "https://www.googleapis.com/userinfo/v2/me?access_token="+accessToken;
		//final String reqURL = "https://www.googleapis.com/oauth2/v1/userinfo?access_token="+accessToken;
		final String rslt = HttpConnectionUtil.getRequest(reqURL, "Bearer " + accessToken);

		if(rslt != null && !rslt.equals("") ) {
			final ObjectMapper mapper = new ObjectMapper();
			final Map userMap = mapper.readValue(rslt, Map.class);
			userInfo.put("id", userMap.get("id"));
			userInfo.put("email", userMap.get("email"));
		}

		return userInfo;
	}

	/**
	 * 구글 refresh_token 으로 access_token 갱신
	 * @param accessToken
	 * @return
	 * @throws Exception
	 */
	public String getRefreshAccesstoken(String refreshToken) throws Exception {
		String accessToken = "";

		final String reqURL = "https://oauth2.googleapis.com/token";

		final Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("grant_type", "refresh_token");
		requestMap.put("client_id", CLIENT_ID);  //본인이 발급받은 key
		requestMap.put("client_secret", CLIENT_SECRET);	    //본인이 발급반은 client secret
		requestMap.put("refresh_token", URLEncoder.encode(refreshToken,"UTF-8"));		// 네이버 사용자 인증에 성공하고 발급받은 갱신 토큰

		final String rslt = HttpConnectionUtil.postRequest(reqURL, "", requestMap);

		if(rslt != null && !rslt.equals("") ) {
			final ObjectMapper mapper = new ObjectMapper();
			final Map tokenMap = mapper.readValue(rslt, Map.class);

			accessToken = (String) tokenMap.get("access_token");

			log.info("##### getRefreshAccesstoken accessToken : " + accessToken);
		}
		return accessToken;
	}

	/**
	 * 구글 토큰 삭제 (연결끊기)
	 * @param accessToken
	 * @throws Exception
	 */
	public Boolean deleteToken(String accessToken) throws Exception {
		final String reqURL ="https://oauth2.googleapis.com/revoke";

		final Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("token", accessToken);

		HttpConnectionUtil.postRequest(reqURL, "", requestMap);

		return true;
	}
}
