package com.ssafy.sharing.application;

import com.ssafy.sharing.domain.Member;

public interface KakaoAPIService {
	
	public String getAccessToken(String authorize_code);
	
	public Member getUserInfo(String access_Token);
	
}
