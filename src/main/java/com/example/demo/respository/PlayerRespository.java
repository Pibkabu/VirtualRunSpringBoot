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
	
	@Query(value = "SELECT * FROM player", nativeQuery = true)
	List<Player> getAllPlayers();
	
	@Query(value = "SELECT * FROM player where UserId = :userId", nativeQuery = true)
	List<Player> getPlayerWithId(@Param("userId") int userId);
	
	@Query(value = "SELECT * FROM player where UserId = :userId and RaceId = :raceId", nativeQuery = true)
	Player getPlayerWithUserIdAndRaceId(@Param("userId") int userId, @Param("raceId") int raceId);
	
	@Query(value = "SELECT * FROM player where RaceId = :raceId", nativeQuery = true)
	List<Player> getRaceParticipants(@Param("raceId") int raceId);
	
	@Query(value = "SELECT * FROM player where RaceId = :raceId and RankInRace > 0 order by RankInRace ASC", nativeQuery = true)
	List<Player> getFinishResult(@Param("raceId") int raceId);
	
	@Query(value = "SELECT * FROM player where RaceId = :raceId and TravelTime > 0 order by TravelTime ASC", nativeQuery = true)
	List<Player> getRaceFinishedParticipants(@Param("raceId") int raceId);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO player(UserId, RaceId) VALUES(:userId,:raceId)", nativeQuery = true)
	void playerRegister(@Param("userId") int userId, @Param("raceId") int raceId);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM player where UserId = :userId and RaceId = :raceId", nativeQuery = true)
	void cancelRegister(@Param("userId") int userId, @Param("raceId") int raceId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE player set TravelTime = ?1, TravelDistance = ?2 where UserId = ?3 and RaceId = ?4", nativeQuery = true)
	void sendResult(double travelTime, double travelDistance, int userId, int raceId );
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE player set RankInRace = :rankInRace where UserId = :userId and RaceId = :raceId", nativeQuery = true)
	void updateRanking(@Param("rankInRace") int rankInRace, @Param("userId") int userId, @Param("raceId") int raceId);
}
