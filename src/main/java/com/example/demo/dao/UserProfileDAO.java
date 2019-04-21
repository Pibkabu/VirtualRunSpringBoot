package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.UserProfile;


public class UserProfileDAO {
	 private List<UserProfile> profiles;
	 
	public UserProfileDAO(List<UserProfile> profiles) {
		this.profiles = profiles;
	}

	public List<UserProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<UserProfile> profiles) {
		this.profiles = profiles;
	}
	 
	 
}
