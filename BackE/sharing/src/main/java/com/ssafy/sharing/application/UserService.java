package com.ssafy.sharing.application;

import com.ssafy.sharing.domain.Member;

public interface UserService {
	public Member getUserinfo(String member_email);

	public boolean checkMember(String member_email);

	public boolean setPassword(Member member);

	public Member test();
}
