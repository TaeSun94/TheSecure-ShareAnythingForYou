package com.ssafy.sharing.application;

import com.ssafy.sharing.domain.Reservation;

public interface ReserveService {
	public boolean checkReserve(Reservation info);

	public void reserveHost(String tid, Reservation reservation_info);
}