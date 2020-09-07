package com.ssafy.sharing.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.sharing.dto.Member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServiceImpl implements TokenService {
	
	/*
	 * member 정보를 JWT토큰으로 인코딩하는 기능
	 * parameterType: User, status, access(default String "MEMBER")
	 * return type: String token
	 * 작성자 : 이한별
	 * 수정일 : 2020-09-07 (월)
	 */
	@Override
	public String createToken(Member member, boolean status, String access) {
		Map<String, Object> header = new HashMap<>();
		header.put("typ", "JWT");
		header.put("alg", "HS256");
		
		Map<String, Object> payload = new HashMap<>();
		payload.put("member_email", member.getMember_email());
		payload.put("status", status);
		payload.put("access", access);
		String jwt = Jwts.builder().setHeader(header).setClaims(payload).signWith(SignatureAlgorithm.HS256, "token".getBytes()).compact();
		return jwt;
	}

	/*
	 * JWT 토큰을 디코딩하여 member 정보로 반환하는 기능
	 * parameter type: token(String)
	 * return type: Map<String, String> => member_email, status, access
	 * 작성자 : 이한별
	 * 수정일 : 2020-09-07 (월)
	 */
	@Override
	public Map<String, String> interpretToken(String token) {
		Map<String, String> result = null;
		try {
			result = new HashMap<>();
			//토큰 자체를 받아온다.
			Claims claim = Jwts.parser().setSigningKey("token".getBytes()).parseClaimsJws(token).getBody();
			//토큰에서 email 정보를 받아서 Member class로 만들어서 반환한다.
			result.put("member_email", claim.get("member_email", String.class));
			result.put("status", ""+claim.get("status", Boolean.class));
			result.put("access", claim.get("access", String.class));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}