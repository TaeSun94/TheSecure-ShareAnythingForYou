package com.ssafy.sharing.application;

import java.util.List;

import com.ssafy.sharing.domain.Transaction;

public interface TransactionService {

	List<Transaction> getTransactionList(String member_email);

	Boolean addTransaction(Transaction transaction);

}
