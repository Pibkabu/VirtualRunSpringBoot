package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DonationDAO;
import com.example.demo.dao.DonationDAO.DonationInfo;
import com.example.demo.model.Donation;
import com.example.demo.model.UserProfile;
import com.example.demo.respository.DonationRepository;
import com.example.demo.respository.UserProfileRespository;

@RestController
public class DonationController {

	@Autowired
	DonationRepository donationRepository;
	
	@Autowired
	UserProfileRespository profileRepository;
	
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
}
