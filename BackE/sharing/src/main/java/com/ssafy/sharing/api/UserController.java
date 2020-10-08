package com.ssafy.sharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.sharing.application.UserService;
import com.ssafy.sharing.domain.Member;
import com.ssafy.sharing.domain.Wallet;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RestController
public class UserController {
	@Autowired
	UserService userService;

	@ApiOperation(value = "member_email을 통해 개인 정보를 조회한다.", response = String.class)
	@GetMapping("/user/info/{member_email:.+}/")
	public ResponseEntity<Member> getUserinfo(
			@ApiParam(value = "useremail", required = true) @PathVariable String member_email) {
		if (!member_email.isEmpty()) {
			Member member = userService.getUserinfo(member_email);
			if (member == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} else {
				
				return new ResponseEntity<>(userService.getUserinfo(member_email), HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "member의 blockchain net password 설정", response = Boolean.class)
	@PutMapping("/user/password")
	public ResponseEntity<Boolean> setPassword(@ApiParam(value = "Member", required = true) @RequestBody Member member){
		if(member == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(userService.setPassword(member), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "테스트", response = Member.class)
	@GetMapping("/test")
	public ResponseEntity<Member> test(){
		return new ResponseEntity<Member>(userService.test(), HttpStatus.OK);
	}
}
