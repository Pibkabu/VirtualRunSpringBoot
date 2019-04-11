package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Donation {
	@Id
	private int donationId;
	private int userId;
	private int raceId;
	private String description;
	private double money;
	
	public Donation() {
	}
	
	public Donation(int donationId, int userId, int raceId, String description, double money) {
		super();
		this.donationId = donationId;
		this.userId = userId;
		this.raceId = raceId;
		this.description = description;
		this.money = money;
	}
	
	public int getDonationId() {
		return donationId;
	}
	public void setDonationId(int donationId) {
		this.donationId = donationId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRaceId() {
		return raceId;
	}
	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	
}
