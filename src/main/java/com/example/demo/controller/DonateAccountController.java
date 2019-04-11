package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DonateAccount;
import com.example.demo.respository.DonateAccountRepositpry;

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
}
