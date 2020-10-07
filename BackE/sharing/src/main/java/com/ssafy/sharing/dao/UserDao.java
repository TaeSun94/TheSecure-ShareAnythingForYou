package com.ssafy.sharing.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.sharing.domain.Member;

@Mapper
public interface UserDao {
	Member getMember(String member_email);

	boolean checkMember(String member_email);

	boolean setPassword(Map<String,Object> map);
}
