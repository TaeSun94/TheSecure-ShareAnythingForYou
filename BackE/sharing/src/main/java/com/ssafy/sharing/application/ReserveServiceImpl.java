package com.ssafy.sharing.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sharing.dao.HostDao;
import com.ssafy.sharing.dao.ReserveDao;
import com.ssafy.sharing.domain.Reservation;

@Service
public class ReserveServiceImpl implements ReserveService {
	@Autowired
	HostDao hostDao;
	
	@Autowired
	ReserveDao reserveDao;

	@Override
	public boolean checkReserve(Reservation info) {
		try {
			int host_num = info.getHost_num();
			String[] reserve_day = info.getReserve_day();
			Map<String, Object> map = new HashMap<>();
			map.put("host_num", host_num);
			map.put("host_available_day", reserve_day);
			int n = hostDao.checkAvailableDay(map);

			if (reserve_day.length == n) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void reserveHost(String tid, Reservation reservation_info) {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("tid", tid);
			map.put("member_email", reservation_info.getMember_email());
			map.put("host_num", reservation_info.getHost_num());
			for(int i = 0; i < reservation_info.getReserve_day().length; i++) {
				if(map.containsKey("reserve_day")) {
					map.replace("reserve_day", reservation_info.getReserve_day()[i]);
				}
				else {
					map.put("reserve_day", reservation_info.getReserve_day()[i]);
				}
				reserveDao.addReservation(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
