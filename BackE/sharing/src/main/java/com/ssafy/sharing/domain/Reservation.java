package com.ssafy.sharing.domain;

import java.util.Arrays;

public class Reservation {
	
	private String member_email;
	private int host_num;
	private String[] reserve_day;
	private int rid;
	private int price;

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public int getHost_num() {
		return host_num;
	}

	public void setHost_num(int host_num) {
		this.host_num = host_num;
	}

	public String[] getReserve_day() {
		return reserve_day;
	}

	public void setReserve_day(String[] reserve_day) {
		this.reserve_day = reserve_day;
	}


	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Reservation [member_email=" + member_email + ", host_num=" + host_num + ", reserve_day="
				+ Arrays.toString(reserve_day) + ", rid=" + rid + ", price=" + price + "]";
	}

}
