package com.ssafy.sharing.domain;

public class Member {
	
	private String member_email;
	private String member_nickname;
	private String member_img;
	private String public_key;
	private String password;
	
	public String getPublic_key() {
		return public_key;
	}

	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_nickname() {
		return member_nickname;
	}

	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}

	public String getMember_img() {
		return member_img;
	}

	public void setMember_img(String member_img) {
		this.member_img = member_img;
	}

	@Override
	public String toString() {
		return "Member [member_email=" + member_email + ", member_nickname=" + member_nickname + ", member_img="
				+ member_img + ", public_key=" + public_key + ", password=" + password + "]";
	}

}
