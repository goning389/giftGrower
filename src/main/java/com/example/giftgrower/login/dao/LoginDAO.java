package com.example.giftgrower.login.dao;/*
package com.example.giftgrower.login.dao;

import com.example.giftgrower.login.vo.LoginInfoVO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 *
 *//*

@Repository("LoginDAO")
public class LoginDAO {

	*/

import com.example.giftgrower.login.vo.LoginVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
	 * 사용자정보 상세
	 * @param loginGbn
	 * @param snsIdnt
	 * @return
	 * @throws Exception
	 *//*

	public LoginInfoVO selectUser(String loginGbn, String snsIdnt) throws Exception {
		final Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("loginGbn",loginGbn);
		paramMap.put("snsIdnt",snsIdnt);
		return getSqlSession().selectOne("LoginDAO.selectUser", paramMap);
	}
}*/
@Mapper
public interface LoginDAO {

	List<LoginVO> selectAllBookInfo();

}
