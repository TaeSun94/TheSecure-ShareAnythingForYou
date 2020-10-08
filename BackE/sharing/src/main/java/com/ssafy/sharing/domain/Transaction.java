package com.ssafy.sharing.domain;

public class Transaction {
	private int txid;
	private String member_email;
	private String tx_hash;
	public int getTxid() {
		return txid;
	}
	public void setTxid(int txid) {
		this.txid = txid;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getTx_hash() {
		return tx_hash;
	}
	public void setTx_hash(String tx_hash) {
		this.tx_hash = tx_hash;
	}
	@Override
	public String toString() {
		return "Transaction [txid=" + txid + ", member_email=" + member_email + ", tx_hash=" + tx_hash + "]";
	}
	
	
}
