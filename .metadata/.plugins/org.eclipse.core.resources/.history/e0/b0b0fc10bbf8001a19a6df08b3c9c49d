package com.ssafy.sharing.application;

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
			Member member = userDao.getUserinfo(member_email);
			if(member.getMember_email().equals(member_email)) {
				return member;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
