package com.ssafy.sharing.service;

import com.ssafy.sharing.dto.Member;

public interface KakaoAPIService {
	
	public String getAccessToken(String authorize_code);
	
	public Member getUserInfo(String access_Token);
	
}
