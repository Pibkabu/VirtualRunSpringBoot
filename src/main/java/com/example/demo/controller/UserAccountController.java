package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserAccount;
import com.example.demo.respository.UserAccountRespository;

@RestController
public class UserAccountController {
	
	@Autowired
	UserAccountRespository userRespository;
	
	@GetMapping("/user")
	public List<UserAccount> getAllUserAccount(){
		return userRespository.findAll();
	}
	
	@GetMapping("/user/email")
	public UserAccount getUserAccountByEmail(@RequestParam String email) {
		UserAccount account = userRespository.getUserAccountByEmail(email);
		if(account == null) {
			account = new UserAccount();
		}
		return account;
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody UserAccount addUserAccount(String email, String password) {
		userRespository.addAnotherUserAccount(email, password);
		return userRespository.getUserAccountByEmail(email);
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody UserAccount accountLogin(String email, String password) {
		UserAccount account = userRespository.getUserAccountByEmail(email);
		if(account == null) {
			account = new UserAccount();
		}
		if(account.getUserId() != 0) {
			if(!account.getPassword().equals(password)) {
				return new UserAccount();
			}
		}
		return account;
	}
	
}
