package com.ssafy.sharing.domain;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class Host {
	private int host_num;
	private String member_email;
	private Member member;
	private String host_address;
	private String host_type;
	private String host_intro;
	private int host_price;
	private String host_capacity;
	private boolean[] host_provide_items = new boolean[10];
	private String[] host_images;
	private MultipartFile[] files;
	private String[] host_available_day;

	public Host() {
	}

	public Host(int host_num, String member_email, Member member, String host_address, String host_type,
			String host_intro, int host_price, String host_capacity, boolean[] host_provide_items, String[] host_images,
			MultipartFile[] files, String[] host_available_day) {
		super();
		this.host_num = host_num;
		this.member_email = member_email;
		this.member = member;
		this.host_address = host_address;
		this.host_type = host_type;
		this.host_intro = host_intro;
		this.host_price = host_price;
		this.host_capacity = host_capacity;
		this.host_provide_items = host_provide_items;
		this.host_images = host_images;
		this.files = files;
		this.host_available_day = host_available_day;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public int getHost_price() {
		return host_price;
	}

	public void setHost_price(int host_price) {
		this.host_price = host_price;
	}

	public String getHost_capacity() {
		return host_capacity;
	}

	public void setHost_capacity(String host_capacity) {
		this.host_capacity = host_capacity;
	}

	public int getHost_num() {
		return host_num;
	}

	public void setHost_num(int host_num) {
		this.host_num = host_num;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getHost_address() {
		return host_address;
	}

	public void setHost_address(String host_address) {
		this.host_address = host_address;
	}

	public String getHost_type() {
		return host_type;
	}

	public void setHost_type(String host_type) {
		this.host_type = host_type;
	}

	public String getHost_intro() {
		return host_intro;
	}

	public void setHost_intro(String host_intro) {
		this.host_intro = host_intro;
	}

	public boolean[] getHost_provide_items() {
		return host_provide_items;
	}

	public void setHost_provide_items(boolean[] host_provide_items) {
		this.host_provide_items = host_provide_items;
	}

	public String[] getHost_images() {
		return host_images;
	}

	public void setHost_images(String[] host_images) {
		this.host_images = host_images;
	}

	public String[] getHost_available_day() {
		return host_available_day;
	}

	public void setHost_available_day(String[] host_available_day) {
		this.host_available_day = host_available_day;
	}

	@Override
	public String toString() {
		return "Host [host_num=" + host_num + ", member_email=" + member_email + ", member=" + member
				+ ", host_address=" + host_address + ", host_type=" + host_type + ", host_intro=" + host_intro
				+ ", host_price=" + host_price + ", host_capacity=" + host_capacity + ", host_provide_items="
				+ Arrays.toString(host_provide_items) + ", host_images=" + Arrays.toString(host_images) + ", files="
				+ Arrays.toString(files) + ", host_available_day=" + Arrays.toString(host_available_day) + "]";
	}

}
