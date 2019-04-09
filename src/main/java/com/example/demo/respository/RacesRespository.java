package com.example.demo.respository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Race;

public interface RacesRespository extends JpaRepository<Race, Integer>{
	
	@Query(value = "SELECT * FROM Race", nativeQuery = true)
	List<Race> getAllRaces();
	
	@Query(value = "SELECT * FROM Race where RaceId = :id", nativeQuery = true)
	Race getRaceById(@Param("id") int id);
	
	@Query(value = "SELECT * FROM Race where distance >= :from and distance <= :to", nativeQuery = true)
	List<Race> getRacesWithDistanceRange(@Param("from") double fromDistance, @Param("to") double toDistance);
	
	@Query(value = "SELECT * FROM Race where EndTime > CURDATE()", nativeQuery = true)
	List<Race> getOngoingRaces();
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Race SET RaceImage = ?1 where raceId = ?2", nativeQuery = true)
	void editRaceImage(String raceImage, int raceId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Race SET StartTime = ?1, EndTime = ?2, RaceName = ?3, Distance = ?4, Regulation = ?5, `Description` = ?6 where raceId = ?7", nativeQuery = true)
	void editRaceInfo(Timestamp startTime, Timestamp endTime, String raceName, double distance, String regulation, String description, int raceId);
}
