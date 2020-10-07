package com.ssafy.sharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.ssafy.sharing.application.ReserveService;
import com.ssafy.sharing.domain.Member;
import com.ssafy.sharing.domain.Reservation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RestController
public class ReserveController {
	@Autowired
	ReserveService reserveService;
	
	// 예약 가능한지 확인
	@ApiOperation(value = "선택한 예약 날짜가 유효한지 확인한다.", response = Boolean.class)
	@GetMapping("/reserve/check")
	public ResponseEntity<Boolean> getUserinfo(
			@ApiParam(value = "Reserve class", required = true) @RequestBody Reservation reservation) {
		return new ResponseEntity<>(reserveService.checkReserve(reservation), HttpStatus.OK);
	}
	
	// 1. 예약 하기
	@ApiOperation(value = "결제가 완료 된 예약 데이터 처리", response = Boolean.class)
	@PostMapping("/reserve/done")
	public ResponseEntity<Reservation> addReserve(@ApiParam(value = "Payment JsonObject", required = true) @RequestBody Reservation reservation){
		
		reserveService.reserveHost(reservation);
		Reservation ret = reserveService.getReserveInfo();
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
}