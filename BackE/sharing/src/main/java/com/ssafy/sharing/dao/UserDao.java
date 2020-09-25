package com.ssafy.sharing.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.sharing.domain.Member;

@Mapper
public interface UserDao {
	Member getMember(String member_email);

	boolean checkMember(String member_email);
}
