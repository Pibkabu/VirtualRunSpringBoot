package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserProfile;
import com.example.demo.respository.UserProfileRespository;
import com.google.gson.Gson;

@RestController
public class UserProfileController {
	@Autowired
	UserProfileRespository userProfileRespository;
	
	@GetMapping("/profile/id")
	public UserProfile getUserProfileWithId(@RequestParam int id) {
		UserProfile profile = userProfileRespository.getUserProfileWithId(id);
		if(profile == null) {
			profile = new UserProfile();
		}
		return profile;
	}
	
	@RequestMapping(value = "/profile/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody UserProfile addUserProfile(String profileJson) {
		Gson gson = new Gson();
		UserProfile profile = new UserProfile();
		profile = gson.fromJson(profileJson, UserProfile.class);
		userProfileRespository.addUserProfile(profile.getUserId(), profile.getDisplayName(), profile.getFirstName(), profile.getLastName(), profile.getDob(), profile.isGender(), profile.getPhone(), profile.getAddress());
		return profile;
	}
	
	@RequestMapping(value = "/profile/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody UserProfile updateUserProfile(String profileJson) {
		Gson gson = new Gson();
		UserProfile profile = new UserProfile();
		profile = gson.fromJson(profileJson, UserProfile.class);
		userProfileRespository.updateUserProfile(profile.getDisplayName(), profile.getFirstName(), profile.getLastName(), profile.getDob(), profile.isGender(), profile.getPhone(), profile.getAddress(), profile.getUserId());
		return profile;
	}
}
