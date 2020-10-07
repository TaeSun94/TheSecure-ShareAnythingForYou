package com.ssafy.sharing.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sharing.dao.TransactionDao;
import com.ssafy.sharing.domain.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	TransactionDao transactionDao;
	
	@Override
	public List<Transaction> getTransactionList(String member_email) {
		try {
			
			List<Transaction> transaction_list = new ArrayList<>();
			transaction_list = transactionDao.getTransactionList(member_email);
			return transaction_list;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean addTransaction(Transaction transaction) {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("member_email", transaction.getMember_email());
			map.put("tx_hash", transaction.getTx_hash());
			return transactionDao.addTransaction(map);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
