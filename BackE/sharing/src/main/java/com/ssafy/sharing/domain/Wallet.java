package com.ssafy.sharing.domain;

public class Wallet {
	private int wallet_id;
	private String address;
	private String owner_id;
	private long balance;
	private int receiving_count;
	private int cash;

	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wallet(int wallet_id, String address, String owner_id, long balance, int receiving_count, int cash) {
		super();
		this.wallet_id = wallet_id;
		this.address = address;
		this.owner_id = owner_id;
		this.balance = balance;
		this.receiving_count = receiving_count;
		this.cash = cash;
	}

	public int getWallet_id() {
		return wallet_id;
	}

	public void setWallet_id(int wallet_id) {
		this.wallet_id = wallet_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(String owner_id) {
		this.owner_id = owner_id;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public int getReceiving_count() {
		return receiving_count;
	}

	public void setReceiving_count(int receiving_count) {
		this.receiving_count = receiving_count;
	}

	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	@Override
	public String toString() {
		return "Wallet [wallet_id=" + wallet_id + ", address=" + address + ", owner_id=" + owner_id + ", balance="
				+ balance + ", receiving_count=" + receiving_count + ", cash=" + cash + "]";
	}

}
