package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.demo.model.Race;

public interface RacesRespository extends JpaRepository<Race, Integer>{
	
	@Query(value = "SELECT * FROM Race", nativeQuery = true)
	List<Race> getAllRaces();
	
	@Query(value = "SELECT * FROM Race where RaceId = :id", nativeQuery = true)
	Race getRaceById(@Param("id") int id);
	
	@Query(value = "SELECT * FROM Race where distance >= :from and distance <= :to", nativeQuery = true)
	List<Race> getRacesWithDistanceRange(@Param("from") double fromDistance, @Param("to") double toDistance);
	
	@Query(value = "SELECT * FROM Race where StartTime > CURDATE()", nativeQuery = true)
	List<Race> getOngoingRaces();
}
