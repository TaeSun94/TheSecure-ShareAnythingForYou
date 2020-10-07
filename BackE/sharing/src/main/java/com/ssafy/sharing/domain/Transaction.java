package com.ssafy.sharing.domain;

public class Transaction {
	private int txid;
	private String member_email;
	private String tx;
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
	public String getTx() {
		return tx;
	}
	public void setTx(String tx) {
		this.tx = tx;
	}
	@Override
	public String toString() {
		return "Transaction [txid=" + txid + ", member_email=" + member_email + ", tx=" + tx + "]";
	}
	
}
