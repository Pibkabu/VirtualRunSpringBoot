package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DonateAccount {
	@Id
	private int raceId;
	private String accountNumber;
	private String accountName;
	
	public DonateAccount() {
	}
	
	public DonateAccount(int raceId, String accountNumber, String accountName) {
		super();
		this.raceId = raceId;
		this.accountNumber = accountNumber;
		this.accountName = accountName;
	}
	
	public int getRaceId() {
		return raceId;
	}
	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	
}
