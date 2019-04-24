package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserAccountDAO;
import com.example.demo.model.UserAccount;
import com.example.demo.respository.UserAccountRespository;
import com.google.gson.Gson;

@RestController
public class UserAccountController {
	
	@Autowired
	UserAccountRespository userRespository;
	
	@GetMapping("/user")
	public UserAccountDAO getAllUserAccount(){
		List<UserAccount> accounts = userRespository.getAllUserAccountINeed();
		if(accounts == null) {
			accounts = new ArrayList<>();
		}
		return new UserAccountDAO(accounts);
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
		UserAccount account = new UserAccount();
		account.setEmail(email);
		account.setPassword(password);
		//userRespository.addAnotherUserAccount(email, password); 
		userRespository.save(account);
		System.out.println(account.getUserId());
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
	
	@RequestMapping(value = "/user/changepassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody UserAccount changePassword(String accountString) {
		Gson gson = new Gson();
		UserAccount account = gson.fromJson(accountString, UserAccount.class);
		if(account == null) {
			return new UserAccount();
		}else {
			userRespository.changePassword(account.getEmail(), account.getPassword());
		}
		return account;
	}
	
}
