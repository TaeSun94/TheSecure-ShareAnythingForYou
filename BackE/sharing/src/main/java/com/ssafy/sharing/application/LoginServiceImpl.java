package com.ssafy.sharing.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sharing.dao.LoginDao;
import com.ssafy.sharing.domain.Member;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;

	/*
	 * 이미 가입 된 멤버인지 확인한 후 가입되지 않았다면 member로 추가해주는 기능
	 * parameterType : Member
	 * 작성자 : 이한별
	 * 수정일 : 2020-09-07 (월)
	 */
	@Override
	public void signupMember(Member member) {
		String member_email = member.getMember_email();
		String member_nickname = member.getMember_nickname();
		String member_img = member.getMember_img();
		try {
			boolean isMember = loginDao.checkMember(member_email);
			if(!isMember) {
				Map<String, Object> map = new HashMap<>();
				map.put("member_email", member_email);
				map.put("member_nickname", member_nickname);
				map.put("member_img", member_img);
				loginDao.insertMember(map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
