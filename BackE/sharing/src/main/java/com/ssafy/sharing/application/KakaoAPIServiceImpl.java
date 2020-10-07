package com.ssafy.sharing.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.transport.http.HttpUrlConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ssafy.sharing.dao.HostDao;
import com.ssafy.sharing.domain.Host;
import com.ssafy.sharing.domain.Member;
import com.ssafy.sharing.domain.Reservation;

@Service
public class KakaoAPIServiceImpl implements KakaoAPIService {

	@Autowired
	HostDao hostDao;

	/*
	 * 카카오 api code를 통해 access token을 얻는 기능 parameterType : String authorize_code
	 * resultType : String access_token 작성자 : 이한별 수정일 : 2020-09-07 (월)
	 */
	@Override
	public String getAccessToken(String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=f67bfd6f7cdb600868d04a4e1408199f");
//			sb.append("&redirect_uri=http://j3c201.p.ssafy.io:80/login");
			sb.append("&redirect_uri=http://localhost:3000/login");

			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();
			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				String result = "";

				while ((line = br.readLine()) != null) {
					result += line;
				}

				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);

				access_Token = element.getAsJsonObject().get("access_token").getAsString();
				refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
				br.close();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return access_Token;
	}

	/*
	 * 위에서 얻은 access token를 통해 실제 유저 정보를 가져오는 기능 parameterType : String access_token
	 * resultType : Member 작성자 : 이한별 수정일 : 2020-09-07 (월)
	 */
	@Override
	public Member getUserInfo(String access_Token) {
		Member member = new Member();
		String reqURL = "https://kapi.kakao.com/v2/user/me";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();
			if (responseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String line = "";
				String result = "";

				while ((line = br.readLine()) != null) {
					result += line;
				}
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);

				JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
				JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

				String nickname = properties.getAsJsonObject().get("nickname").toString();
//				String email = kakao_account.getAsJsonObject().get("email").toString();
				String email = element.getAsJsonObject().get("id").toString();
				String img = "";
				if (properties.getAsJsonObject().get("profile_image") != null) {
					img = properties.getAsJsonObject().get("profile_image").toString();
				}
				member.setMember_email(email);
				member.setMember_nickname(nickname);
				member.setMember_img(img);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return member;
	}

	@Override
	public JsonObject getPayment(Reservation info) {
		try {
			URL url = new URL("https://kapi.kakao.com/v1/payment/ready");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "KakaoAK 0c41b4d5630d03f84648f01601d86b11");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			Host host = hostDao.getHost(info.getHost_num());
			Map<String, String> params = new HashMap<String, String>();
			params.put("cid", "TC0ONETIME");
			params.put("partner_order_id", host.getMember_email());
			params.put("partner_user_id", info.getMember_email());
			params.put("item_name", host.getHost_address());
			params.put("quantity", Integer.toString(info.getReserve_day().length));
			params.put("total_amount", Integer.toString(host.getHost_price() * info.getReserve_day().length));
			params.put("tax_free_amount", Integer.toString(0));
			params.put("vat_amount", Integer.toString(0));
			params.put("approval_url", "http://localhost:3000/payment/approval");
			params.put("cancel_url", "http://localhost:3000/payment/cancel");
			params.put("fail_url", "http://localhost:3000/payment/fail");

			String string_params = new String();
			for (Map.Entry<String, String> elem : params.entrySet()) {
				string_params += (elem.getKey() + "=" + elem.getValue() + "&");
			}

			conn.getOutputStream().write(string_params.getBytes());

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			JsonParser parser = new JsonParser();
			JsonObject obj = (JsonObject) parser.parse(in);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public JsonObject done(JsonObject obj) {
		try {
			URL url = new URL("https://kapi.kakao.com/v1/payment/approve");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "KakaoAK 0c41b4d5630d03f84648f01601d86b11");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			conn.setDoInput(true);
			conn.setDoOutput(true);

			Map<String, String> params = new HashMap<String, String>();
			params.put("cid", "TC0ONETIME");
			params.put("tid", obj.get("tid").toString());
			params.put("partner_order_id", obj.get("partner_order_id").toString());
			params.put("partner_user_id", obj.get("partner_user_id").toString());
			params.put("pg_token", obj.get("pg_token").toString());

			String string_params = new String();
			for (Map.Entry<String, String> elem : params.entrySet()) {
				string_params += (elem.getKey() + "=" + elem.getValue() + "&");
			}

			conn.getOutputStream().write(string_params.getBytes());

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			JsonParser parser = new JsonParser();
			JsonObject retObj = (JsonObject) parser.parse(in);
			return retObj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
