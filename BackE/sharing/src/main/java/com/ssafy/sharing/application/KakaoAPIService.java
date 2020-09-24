package com.ssafy.sharing.application;

import com.google.gson.JsonObject;
import com.ssafy.sharing.domain.Host;
import com.ssafy.sharing.domain.Member;
import com.ssafy.sharing.domain.Reservation;

public interface KakaoAPIService {
	
	public String getAccessToken(String authorize_code);
	
	public Member getUserInfo(String access_Token);
	
	public JsonObject getPayment(Reservation info);

	public JsonObject done(JsonObject obj);
}
