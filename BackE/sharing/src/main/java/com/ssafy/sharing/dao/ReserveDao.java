package com.ssafy.sharing.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReserveDao {

	void addReservation(Map<String, Object> map);
	int getRid(String tid);
	void addReserveDay(Map<String, Object> map);
}
