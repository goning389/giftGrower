/*
package com.example.giftgrower.login.service.impl;

import com.example.giftgrower.login.dao.LoginDAO;
import com.example.giftgrower.login.service.LoginService;
import com.example.giftgrower.login.vo.LoginInfoVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

*/
/**
 *
 *//*

@Service("LoginService")
public class LoginServiceImpl implements LoginService
{
	protected static final Log log = LogFactory.getLog(LoginServiceImpl.class);

	@Resource(name = "LoginDAO")
	private LoginDAO loginDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Map<String, Object> processSnsLogin(String loginGbn, String snsIdnt, String email) throws Exception {
		final Map<String, Object> returnMap = new HashMap<>();

		final LoginInfoVO loginInfo = loginDao.selectUser(loginGbn,snsIdnt);

		if (loginInfo == null) {
			log.info("##### loginInfo == null 연동 계정이 없습니다.");
			returnMap.put("loginResultContent", "해당 계정으로 연동된 회원정보가 없습니다.");
		}

		final String accntSttsCd = loginInfo.getAccntSttsCd();

		if (!"1".equals(accntSttsCd)) {	//활성상태가 아닌 경우
			if ("30".equals(accntSttsCd) || "40".equals(accntSttsCd) || "50".equals(accntSttsCd)) {	//휴면 체크 (30:휴면(6개월), 40:휴면(12개월), 50:휴면(영구))
				returnMap.put("loginResultContent", "회원님의 계정은 현재 휴면 상태입니다.");
			} else if ("20".equals(accntSttsCd)) {	//정지 체크 (20:비활성)
				returnMap.put("loginResultContent", "회원님의 계정은 현재 정지 상태입니다.");
			} else if ("11".equals(accntSttsCd)) {	//승인대기 체크 (11:승인대기)
				returnMap.put("loginResultContent", "회원님의 계정은 현재 승인대기 상태입니다.");
			}
			returnMap.put("loginResult", "N");
			return returnMap;
		}
		returnMap.put("loginResultContent", "성공적으로 로그인되었습니다.");
		returnMap.put("loginResult", "Y");
		returnMap.put("loginInfo", loginInfo);

		return returnMap;
	}
}*/
