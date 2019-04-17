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
	
	@Query(value = "SELECT * FROM race", nativeQuery = true)
	List<Race> getAllRaces();
	
	@Query(value = "SELECT * FROM race where RaceId = :id", nativeQuery = true)
	Race getRaceById(@Param("id") int id);
	
	@Query(value = "SELECT * FROM race where Distance >= :from and Distance <= :to", nativeQuery = true)
	List<Race> getRacesWithDistanceRange(@Param("from") double fromDistance, @Param("to") double toDistance);
	
	@Query(value = "SELECT * FROM race where EndTime > CURDATE() order by RaceId DESC", nativeQuery = true)
	List<Race> getOngoingRaces();
	
	@Query(value = "SELECT * FROM race where EndTime < CURDATE() order by RaceId DESC", nativeQuery = true)
	List<Race> getAllEndedRaces();
	
	@Query(value = "SELECT * from race where RaceName like '%:name%'", nativeQuery = true)
	List<Race> getRacesWithName(@Param("name") String name);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE race SET RaceImage = ?1 where RaceId = ?2", nativeQuery = true)
	void editRaceImage(String raceImage, int raceId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE race SET StartTime = ?1, EndTime = ?2, RaceName = ?3, Distance = ?4, Regulation = ?5, `Description` = ?6 where RaceId = ?7", nativeQuery = true)
	void editRaceInfo(Timestamp startTime, Timestamp endTime, String raceName, double distance, String regulation, String description, int raceId);
	
	@Transactional
	@Modifying
	@Query(value = " INSERT INTO race (CreateTime, StartTime, EndTime, RaceName, Distance, Regulation, `Description`, RacePassword) values (CURDATE(),?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
	void addRace(Timestamp startTime, Timestamp endTime, String raceName, double distance, String regulation, String description, String racePassword);
	
	@Query(value = "SELECT LAST_INSERT_ID()", nativeQuery = true)
	String getLastInserted();
}
