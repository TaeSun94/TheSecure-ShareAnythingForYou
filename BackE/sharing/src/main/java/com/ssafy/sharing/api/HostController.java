package com.ssafy.sharing.api;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.sharing.application.HostService;
import com.ssafy.sharing.application.UserService;
import com.ssafy.sharing.domain.Host;
import com.ssafy.sharing.domain.Member;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RestController
public class HostController {
	@Autowired
	HostService hostService;
	@Autowired
	UserService userService;
	
	@ApiOperation(value = "sharing home을 등록한다.", response = Boolean.class)
	@PostMapping("/host/regist")
	public ResponseEntity<Boolean> registHost(
			@ApiParam(value = "Host object", required = true) @RequestBody Host host) {
		if (host == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(hostService.registHost(host),HttpStatus.OK);

	}
	
	@ApiOperation(value = "등록된 sharing home을 제거한다.", response = Boolean.class)
	@DeleteMapping("/host/delete")
	public ResponseEntity<Boolean> deleteHost(@ApiParam(value = "Host Number", required = true)@RequestBody int host_num){
		return new ResponseEntity<>(hostService.deleteHost(host_num),HttpStatus.OK);
	}

	@ApiOperation(value = "등록된 sharing home 리스트를 불러온다.", response = Host.class)
	@GetMapping("/host/read")
	public ResponseEntity<List<Host>> getHosts(@ApiParam(value = "member_email", required = true)@RequestBody String member_email){
		if(!userService.checkMember(member_email)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		List<Host> host_list = hostService.getHosts(member_email);
		return new ResponseEntity<>(host_list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "등록된 sharing home의 특정 부분을 수정한다.", response = Boolean.class)
	@PutMapping("/host/update")
	public ResponseEntity<Boolean> updateHost(@ApiParam(value = "Host Class", required = true)@RequestBody Host host){
		if(host == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(hostService.updateHost(host), HttpStatus.OK);
	}
}
