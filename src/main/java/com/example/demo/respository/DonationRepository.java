package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Donation;

public interface DonationRepository extends JpaRepository<Donation, Integer>{
	@Query(value = "SELECT * FROM donation where RaceId = :raceId order by DonationId DESC", nativeQuery = true)
	List<Donation> getRaceDonation(@Param("raceId") int raceId);
}
