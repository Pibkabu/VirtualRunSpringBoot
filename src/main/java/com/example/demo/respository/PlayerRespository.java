package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Player;
import com.example.demo.model.UserAndRaceMaped;

public interface PlayerRespository extends JpaRepository<Player, UserAndRaceMaped>{
	
	@Query(value = "SELECT * FROM Player", nativeQuery = true)
	List<Player> getAllPlayers();
	
	@Query(value = "SELECT * FROM Player where userId = :userId", nativeQuery = true)
	List<Player> getPlayerWithId(@Param("userId") int userId);
	
}
