package com.example.demo.respository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.UserProfile;

public interface UserProfileRespository extends JpaRepository<UserProfile, Integer>{
	
	@Query(value = "SELECT * FROM UserProfile where userId = :id", nativeQuery = true)
	UserProfile getUserProfileWithId(@Param("id") int id);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO UserProfile VALUE(?1,?2,?3,?4,?5,?6,?7,?8)", nativeQuery = true)
	void addUserProfile(int userid, String displayname, String firstname, String lastname, Timestamp dob, boolean gender, String phone, String address);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE UserProfile SET displayName = ?1, firstName = ?2, lastName = ?3, DOB = ?4, gender = ?5, phone = ?6, address = ?7 where userId = ?8", nativeQuery = true)
	void updateUserProfile(String displayname, String firstname, String lastname, Timestamp dob, boolean gender, String phone, String address, int userid);
}
