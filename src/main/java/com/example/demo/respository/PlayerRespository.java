package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Player;
import com.example.demo.model.UserAndRaceMaped;

public interface PlayerRespository extends JpaRepository<Player, UserAndRaceMaped>{
	
	@Query(value = "SELECT * FROM Player", nativeQuery = true)
	List<Player> getAllPlayers();
	
	@Query(value = "SELECT * FROM Player where userId = :userId", nativeQuery = true)
	List<Player> getPlayerWithId(@Param("userId") int userId);
	
	@Query(value = "SELECT * FROM Player where userId = :userId and raceId = :raceId", nativeQuery = true)
	Player getPlayerWithUserIdAndRaceId(@Param("userId") int userId, @Param("raceId") int raceId);
	
	@Query(value = "SELECT * FROM Player where raceId = :raceId", nativeQuery = true)
	List<Player> getRaceParticipants(@Param("raceId") int raceId);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO Player(UserId, RaceId) VALUES(:userId,:raceId)", nativeQuery = true)
	void playerRegister(@Param("userId") int userId, @Param("raceId") int raceId);
}
