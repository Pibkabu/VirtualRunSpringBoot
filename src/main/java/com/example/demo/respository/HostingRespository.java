package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.UserAndRaceMaped;
import com.example.demo.model.UserHost;

public interface HostingRespository extends JpaRepository<UserHost, UserAndRaceMaped>{
	
	@Query(value = "SELECT * FROM UserHost where userId = :userId", nativeQuery = true)
	List<UserHost> getRacesUserHosting(@Param("userId") int userId);
}
