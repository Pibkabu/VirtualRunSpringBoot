package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PlayerListDAO;
import com.example.demo.dao.RacesListDAO;
import com.example.demo.dao.UserProfileDAO;
import com.example.demo.model.Player;
import com.example.demo.model.Race;
import com.example.demo.model.UserProfile;
import com.example.demo.respository.PlayerRespository;
import com.example.demo.respository.RacesRespository;
import com.example.demo.respository.UserProfileRespository;
import com.google.gson.Gson;

@RestController
public class PlayerController {
	
	@Autowired
	PlayerRespository playerRespository;
	
	@Autowired
	RacesRespository raceRespository;
	
	@Autowired
	UserProfileRespository userProfileRespository;
	
	@GetMapping("/players")
	public PlayerListDAO getAllPlayers() {
		List<Player> players = playerRespository.getAllPlayers();
		return new PlayerListDAO(players);
	}
	
	@GetMapping("/players/profile")
	public UserProfileDAO getPlayerProfile(@RequestParam int raceId) {
		List<Player> players = playerRespository.getRaceParticipants(raceId);
		List<UserProfile> profiles = new ArrayList<>();
		
		for (Player player : players) {
			UserProfile profile = userProfileRespository.getUserProfileWithId(player.getUserAndRaceMaped().getUserId());
			profiles.add(profile);
		}
		
		return new UserProfileDAO(profiles);
	}
	
	@GetMapping("/players/id")
	public PlayerListDAO getPlayerWithId(@RequestParam int userId) {
		List<Player> players = playerRespository.getPlayerWithId(userId);
		if(players == null) {
			players = new ArrayList<Player>();
		}
		return new PlayerListDAO(players);
	}
	
	@GetMapping("/players/attending/ongoing")
	public RacesListDAO getAttendingRaces(@RequestParam int userId) {
		List<Player> players = playerRespository.getPlayerWithId(userId);
		List<Race> races = raceRespository.getOngoingRaces();
		List<Race> ongoingAtteding = new ArrayList<>();
		if(players != null && !players.isEmpty() && races != null && !races.isEmpty()) {
			for (Race race : races) {
				for (Player attened : players) {
					if(race.getRaceId() == attened.getUserAndRaceMaped().getRaceId() && attened.getRankInRace() == 0) {
						ongoingAtteding.add(race);
						break;
					}
				}
			}
		}
		return new RacesListDAO(ongoingAtteding);
	}
	
	@GetMapping("/players/race")
	public PlayerListDAO getRaceParticipants(@RequestParam int raceId) {
		List<Player> players = playerRespository.getRaceParticipants(raceId);
		if(players == null) {
			players = new ArrayList<Player>();
		}
		return new PlayerListDAO(players);
	}
	
	@GetMapping("/players/race/finish")
	public PlayerListDAO getFinishResult(@RequestParam int raceId) {
		List<Player> players = playerRespository.getFinishResult(raceId);
		if(players == null) {
			players = new ArrayList<Player>();
		}
		return new PlayerListDAO(players);
	}
	
	@RequestMapping(value = "/players/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody Player playerRegister(String paticipant) {
		Gson gson = new Gson();
		Player player = gson.fromJson(paticipant, Player.class);
		if(player != null) {
			playerRespository.playerRegister(player.getUserAndRaceMaped().getUserId(), player.getUserAndRaceMaped().getRaceId());
		}else {
			player = new Player();
		}
		return player;
	}
	
	@RequestMapping(value = "/players/register/cancel", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody Player cancelRegister(String paticipant) {
		Gson gson = new Gson();
		Player player = gson.fromJson(paticipant, Player.class);
		if(player != null) {
			playerRespository.cancelRegister(player.getUserAndRaceMaped().getUserId(), player.getUserAndRaceMaped().getRaceId());
		}else {
			player = new Player();
		}
		return player;
	}
	
	@RequestMapping(value = "/players/sendResult", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody Player sendResult(String record) {
		Gson gson = new Gson();
		Player player = gson.fromJson(record, Player.class);
		if(player != null) {
			Race race = raceRespository.getRaceById(player.getUserAndRaceMaped().getRaceId());
			if(player.getTravelDistance() < race.getDistance()) {
				return new Player();
			}else {
				playerRespository.sendResult(player.getTravelTime(), player.getTravelDistance(),
						player.getUserAndRaceMaped().getUserId(), player.getUserAndRaceMaped().getRaceId());
				List<Player> participants = playerRespository.getRaceFinishedParticipants(player.getUserAndRaceMaped().getRaceId());
				if(!participants.isEmpty()) {
					for (int i = 0; i < participants.size(); i++) {
						if(participants.get(i).getUserAndRaceMaped().getUserId() == player.getUserAndRaceMaped().getUserId()) {
							player.setRankInRace(i+1);
						}
						playerRespository.updateRanking((i+1), participants.get(i).getUserAndRaceMaped().getUserId(),
								participants.get(i).getUserAndRaceMaped().getRaceId());
					}
				}
			}
		}else {
			player = new Player();
		}
		return player;
	}
}
