package com.ssafy.sharing.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.sharing.domain.Member;
import com.ssafy.sharing.application.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RestController
public class LoginController {
	
	@Autowired
	private KakaoAPIService kakaoService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "카카오 API code의 값을 전달받아서 userinfo를 얻은 후 email과 nickname이 담겨있는 member객체를 반환한다..", response = Member.class)
	@PostMapping("/login")
	public ResponseEntity<Member> login(@ApiParam(value = "kakao api code", required = true) @RequestBody String code) {
		String access_Token = kakaoService.getAccessToken(code);
		
		Member member = kakaoService.getUserInfo(access_Token);
		if(member.getMember_email() != null) {
			loginService.signupMember(member);

			return new ResponseEntity<>(userService.getUserinfo(member.getMember_email()), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
}
