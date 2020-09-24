package com.ssafy.sharing.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.ssafy.sharing.application.HostService;
import com.ssafy.sharing.application.KakaoAPIService;
import com.ssafy.sharing.application.ReserveService;
import com.ssafy.sharing.domain.Host;
import com.ssafy.sharing.domain.Member;
import com.ssafy.sharing.domain.Reservation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RestController
public class KakaopayController {

	@Autowired
	HostService hostService;
	
	@Autowired
	KakaoAPIService kakaoAPIService;
	
	@Autowired
	ReserveService reserveService;
	
	@ApiOperation(value = "결제페이지로 이동할 URL을 얻는다.", response = String.class)
	@PostMapping("/payment/ready")
	public ResponseEntity<JsonObject> ready(@ApiParam(value = "Reservation class", required = true) @RequestBody Reservation reservation_info) throws IOException {
		Host host = hostService.getHost(reservation_info.getHost_num());
		if(hostService.getHost(host.getHost_num()) == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		JsonObject obj = kakaoAPIService.getPayment(reservation_info);
		obj.add("pg_token", null);
//		String tid = obj.get("tid").toString();
//		reserveService.reserveHost(tid, reservation_info);
		return new ResponseEntity<>(obj,HttpStatus.OK);
	}
	
	@ApiOperation(value = "결제 승인 후 완료 과정", response = String.class)
	@PostMapping("/payment/done")
	public ResponseEntity<JsonObject> done(@ApiParam(value = "payment JsonObject", required = true) @RequestBody JsonObject obj){
		JsonObject result = kakaoAPIService.done(obj);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
