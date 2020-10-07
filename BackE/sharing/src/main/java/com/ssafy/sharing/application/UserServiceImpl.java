package com.ssafy.sharing.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sharing.dao.UserDao;
import com.ssafy.sharing.domain.Member;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public Member getUserinfo(String member_email) {
		try {
			Member member = userDao.getMember(member_email);
			if (member.getMember_email().equals(member_email)) {
				return member;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean checkMember(String member_email) {
		try {
			return userDao.checkMember(member_email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean setPassword(Member member) {
		try {
			Map<String,Object> map = new HashMap<>();
			map.put("member_email", member.getMember_email());
			map.put("password", member.getPassword());
			map.put("public_key", member.getPublic_key());
			
			return userDao.setPassword(map);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
