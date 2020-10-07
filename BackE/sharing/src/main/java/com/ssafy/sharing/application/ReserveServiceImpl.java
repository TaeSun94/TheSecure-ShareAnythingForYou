package com.ssafy.sharing.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	public void reserveHost(Reservation reservation) {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("member_email", reservation.getMember_email());
			map.put("host_num", reservation.getHost_num());
			reserveDao.addReservation(map);
			int rid = reserveDao.getRid();
			map.put("rid", rid);
			for(int i = 0; i < reservation.getReserve_day().length; i++) {
				if(map.containsKey("reserve_day")) {
					map.replace("reserve_day", reservation.getReserve_day()[i]);
				}
				else {
					map.put("reserve_day", reservation.getReserve_day()[i]);
				}
				reserveDao.addReserveDay(map);
				hostDao.updateAvailableDay(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Reservation getReserveInfo() {
		try {
			Reservation ret = reserveDao.getLatelyReserve();
			List<String> reserve_day_list = new ArrayList<>();
//			reserve_day_list = reserveDao.getReserveDays()
//			ret.setReserve_day(reserve_day);
			return ret;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
