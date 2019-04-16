package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Donation;
import com.example.demo.model.UserProfile;

public class DonationDAO {
	private List<DonationInfo> infos;
    
	public DonationDAO() {
	}
	
	public List<DonationInfo> getInfos() {
		return infos;
	}

	public void setInfos(List<DonationInfo> infos) {
		this.infos = infos;
	}

	public static class DonationInfo{
	    private UserProfile profile;
	    private Donation donation;
	    
		public DonationInfo() {
		}

		public DonationInfo(UserProfile profile, Donation donation) {
			this.profile = profile;
			this.donation = donation;
		}
		
		public UserProfile getProfile() {
			return profile;
		}
		public void setProfile(UserProfile profile) {
			this.profile = profile;
		}
		public Donation getDonation() {
			return donation;
		}
		public void setDonation(Donation donation) {
			this.donation = donation;
		}
	}
}
