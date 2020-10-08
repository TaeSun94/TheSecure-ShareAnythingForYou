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
			map.put("price", reservation.getPrice());
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
			reserve_day_list = reserveDao.getReserveDays(ret.getRid());
			String[] reserve_days = new String[reserve_day_list.size()];
			for(int i = 0; i < reserve_days.length; i++) {
				reserve_days[i] = reserve_day_list.get(i);
			}
			ret.setHost(hostDao.getHost(ret.getHost_num()));
			ret.setReserve_day(reserve_days);
			return ret;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reservation> getMyReservations(String member_email) {
		try {
			List<Reservation> reserve_list = new ArrayList<>();
			reserve_list = reserveDao.getMyReservations(member_email);
			for(Reservation reservation : reserve_list) {
				List<String> reserve_day_list = new ArrayList<>();
				reserve_day_list = reserveDao.getReserveDays(reservation.getRid());
				String[] reserve_days = new String[reserve_day_list.size()];
				for(int i = 0; i < reserve_days.length; i++) {
					reserve_days[i] = reserve_day_list.get(i);
				}
				reservation.setHost(hostDao.getHost(reservation.getHost_num()));
				reservation.setReserve_day(reserve_days);
			}
			return reserve_list;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
