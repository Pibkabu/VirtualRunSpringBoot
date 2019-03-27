package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.UserAndRaceMaped;
import com.example.demo.model.UserHost;

public interface HostingRespository extends JpaRepository<UserHost, UserAndRaceMaped>{
	
	@Query(value = "SELECT * FROM UserHost where userId = :userId", nativeQuery = true)
	List<UserHost> getRacesUserHosting(@Param("userId") int userId);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO UserHost values(:userId, :raceId)", nativeQuery = true)
	void addHosting(@Param("userId") int userId, @Param("raceId") int raceId);
}
