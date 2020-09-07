package com.ssafy.sharing.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {
	
	/*
	 * DB에 존재하는 멤버인지 확인하는 기능
	 * parameterType : String member_email
	 * resultType : boolean => true : 존재 / false : 존재하지 않음
	 * 작성자 : 이한별
	 * 수정일 : 2020-09-07 (월)
	 */
	boolean checkMember(String member_email);

	/*
	 * DB member table에 meber로 추가하는 기능
	 * parametertType : Map<String,String> => member_email, member_nickname
	 * 작성자 : 이한별
	 * 수정일 : 2020-09-07 (월)
	 */
	void insertMember(Map<String, Object> map);

}
