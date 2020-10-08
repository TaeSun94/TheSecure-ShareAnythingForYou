package com.ssafy.sharing.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.sharing.application.TransactionService;
import com.ssafy.sharing.application.UserService;
import com.ssafy.sharing.domain.Member;
import com.ssafy.sharing.domain.Transaction;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
@RestController
public class TransactionController {
	@Autowired
	TransactionService transactionService;

	@ApiOperation(value = "insert transaction", response = Boolean.class)
	@PostMapping("/transaction/insert")
	public ResponseEntity<Boolean> addTransaction(@ApiParam(value = "transaction", required=true) @RequestBody Transaction transaction){
		if(transaction == null) {
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Boolean>(transactionService.addTransaction(transaction),HttpStatus.OK);
	}
	
	@ApiOperation(value = "member_email을 통해 transaction List를 조회한다", response = String.class)
	@GetMapping("/transaction/{member_email}/")
	public ResponseEntity<List<Transaction>> getTransactionList(
			@ApiParam(value = "member_email", required = true) @PathVariable String member_email) {
		if (!member_email.isEmpty()) {
			List<Transaction> transaction_list = new ArrayList<>();
			transaction_list = transactionService.getTransactionList(member_email);
			return new ResponseEntity<List<Transaction>>(transaction_list, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
}
