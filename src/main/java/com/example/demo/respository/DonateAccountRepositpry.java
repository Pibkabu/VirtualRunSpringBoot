package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.DonateAccount;

public interface DonateAccountRepositpry extends JpaRepository<DonateAccount, Integer>{
	
	@Query(value = "SELECT * FROM DonateAccount where raceId = :raceId", nativeQuery = true)
	DonateAccount getDonateAccountOfRace(@Param("raceId") int raceId);
}
