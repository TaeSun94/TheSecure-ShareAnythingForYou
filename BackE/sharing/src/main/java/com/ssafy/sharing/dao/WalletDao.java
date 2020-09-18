package com.ssafy.sharing.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.sharing.domain.Wallet;


@Mapper
public interface WalletDao {
	public boolean createWallet(Map<String,Object> map);

	public Wallet getWallet(String owner_id);
}
