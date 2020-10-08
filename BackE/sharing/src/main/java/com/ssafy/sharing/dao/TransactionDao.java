package com.ssafy.sharing.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.sharing.domain.Transaction;

@Mapper
public interface TransactionDao {

	List<Transaction> getTransactionList(String member_email);

	Boolean addTransaction(Map<String, Object> map);

}
