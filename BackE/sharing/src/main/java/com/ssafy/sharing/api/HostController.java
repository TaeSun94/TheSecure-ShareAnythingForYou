package com.ssafy.sharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.sharing.application.HostService;
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
	
	@ApiOperation(value = "sharing home을 등록한다.", response = Host.class)
	@PutMapping("/host/regist")
	public ResponseEntity<Boolean> getUserinfo(
			@ApiParam(value = "Host object", required = true) @RequestBody Host host) {
		if (host == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(hostService.registHost(host),HttpStatus.OK);

	}

}
