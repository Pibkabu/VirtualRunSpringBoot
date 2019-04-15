package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.DonateAccount;

public interface DonateAccountRepositpry extends JpaRepository<DonateAccount, Integer>{
	
	@Query(value = "SELECT * FROM donateaccount where RaceId = :raceId", nativeQuery = true)
	DonateAccount getDonateAccountOfRace(@Param("raceId") int raceId);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO donateaccount(RaceId, AccountNumber, AccountName) VALUES(:raceId, :number, :name)", nativeQuery = true)
	void addDonateAccount(@Param("raceId") int raceId, @Param("number") String accountNumber, @Param("name") String accountName);
}
