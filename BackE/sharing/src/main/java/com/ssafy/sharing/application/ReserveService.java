package com.ssafy.sharing.application;

import java.util.List;

import com.ssafy.sharing.domain.Reservation;

public interface ReserveService {
	public boolean checkReserve(Reservation info);

	public void reserveHost(Reservation reservation);

	public Reservation getReserveInfo();

	public List<Reservation> getMyReservations(String member_email);
}
