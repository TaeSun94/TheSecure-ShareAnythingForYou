package com.ssafy.sharing.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.sharing.domain.Reservation;

@Mapper
public interface ReserveDao {

	void addReservation(Map<String, Object> map);

	int getRid();

	void addReserveDay(Map<String, Object> map);

	Reservation getLatelyReserve();

	List<String> getReserveDays(int rid);
}
