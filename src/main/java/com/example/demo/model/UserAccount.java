package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class UserAccount {
	@Id
	@Column(name="UserId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	private String email;
	
	@Column(name = "UserPassword")
	private String password;
	
	public UserAccount() {
		
	}
	
	public UserAccount(int userId, String email, String password) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String userName) {
		this.email = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
