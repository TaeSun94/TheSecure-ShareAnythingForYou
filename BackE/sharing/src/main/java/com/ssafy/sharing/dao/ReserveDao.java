package com.ssafy.sharing.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReserveDao {

	void addReservation(Map<String, Object> map);
	
}
