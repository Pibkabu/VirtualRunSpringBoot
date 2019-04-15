package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DonateAccount;
import com.example.demo.respository.DonateAccountRepositpry;
import com.google.gson.Gson;

@RestController
public class DonateAccountController {
	
	@Autowired
	DonateAccountRepositpry accountRepositpry;
	
	@GetMapping("/donate/account")
	public DonateAccount getDonateAccountOfRace(@RequestParam int raceId) {
		DonateAccount account = accountRepositpry.getDonateAccountOfRace(raceId);
		if(account == null) {
			account = new DonateAccount();
		}
		return account;
	}
	
	@RequestMapping(value = "/donate/account/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody DonateAccount addDonateAccount(String account) {
		Gson gson = new Gson();
		DonateAccount donateAccount = gson.fromJson(account, DonateAccount.class);
		accountRepositpry.addDonateAccount(donateAccount.getRaceId(), donateAccount.getAccountNumber(), donateAccount.getAccountName());
		return donateAccount;
	}
}
