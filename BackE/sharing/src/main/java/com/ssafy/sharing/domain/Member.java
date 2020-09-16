package com.ssafy.sharing.domain;

public class Member {
	
	private String member_email;
	private String member_nickname;

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

	@Override
	public String toString() {
		return "Member [member_email=" + member_email + ", member_nickname=" + member_nickname + "]";
	}

}
