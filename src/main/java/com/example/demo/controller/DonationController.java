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

import com.example.demo.dao.DonationDAO;
import com.example.demo.dao.DonationDAO.DonationInfo;
import com.example.demo.model.Donation;
import com.example.demo.model.UserAccount;
import com.example.demo.model.UserProfile;
import com.example.demo.respository.DonationRepository;
import com.example.demo.respository.UserAccountRespository;
import com.example.demo.respository.UserProfileRespository;

@RestController
public class DonationController {

	@Autowired
	DonationRepository donationRepository;
	
	@Autowired
	UserProfileRespository profileRepository;
	
	@Autowired
	UserAccountRespository accountRespository;
	
	@GetMapping("/donation")
	public DonationDAO getRaceDonation(@RequestParam int raceId){
		List<DonationInfo> infos = new ArrayList<>();
		DonationDAO dao = new DonationDAO();
		List<Donation> donations = donationRepository.getRaceDonation(raceId);
		if(donations != null && !donations.isEmpty()) {
			for (Donation donation : donations) {
				UserProfile profile = profileRepository.getUserProfileWithId(donation.getUserId());
				infos.add(new DonationInfo(profile, donation));
			}
			dao.setInfos(infos);
		}
		return dao;
	}
	
	@RequestMapping(value = "/donation/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody DonationInfo addDonation(String raceId, String email, String description, String money) {
		UserAccount account = accountRespository.getUserAccountByEmail(email);
		DonationInfo info = new DonationInfo();
		if(account != null) {
			UserProfile profile = profileRepository.getUserProfileWithId(account.getUserId());
			donationRepository.addDonation(Integer.valueOf(raceId), account.getUserId(), Double.valueOf(money), description);
			Donation donation = new Donation();
			donation.setUserId(account.getUserId());
			donation.setRaceId(Integer.valueOf(raceId));
			donation.setMoney(Double.valueOf(money));
			donation.setDescription(description);
			info.setDonation(donation);
			info.setProfile(profile);
		}else {
			info.setDonation(new Donation());
			info.setProfile(new UserProfile());
		}
		return info;
	}
}
