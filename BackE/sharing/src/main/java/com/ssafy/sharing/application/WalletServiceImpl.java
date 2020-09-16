package com.ssafy.sharing.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.sharing.dao.WalletDao;
import com.ssafy.sharing.domain.Wallet;

@Service
public class WalletServiceImpl implements WalletService {
	@Autowired
	WalletDao walletDao;

	@Override
	public Wallet register(Wallet wallet) {
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("owner_id", wallet.getOwner_id());
			map.put("address", wallet.getAddress());
			map.put("balance", wallet.getBalance());
			map.put("receiving_count", 0);
			map.put("cash", 0);
			if (walletDao.createWallet(map)) {
				return walletDao.getWallet(wallet.getOwner_id());
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
