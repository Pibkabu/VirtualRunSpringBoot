package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Integer>{
	@Query(value = "SELECT * FROM donation where RaceId = :raceId", nativeQuery = true)
	List<Donation> getRaceDonation(@Param("raceId") int raceId);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO donation (RaceId, UserId, Money, Description) values (?1,?2,?3,?4)", nativeQuery = true)
	void addDonation(int raceId, int userId, double money, String description);
}
