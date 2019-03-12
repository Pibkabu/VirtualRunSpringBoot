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
import com.example.demo.model.Player;
import com.example.demo.respository.PlayerRespository;
import com.google.gson.Gson;

@RestController
public class PlayerController {
	
	@Autowired
	PlayerRespository playerRespository;
	
	@GetMapping("/players")
	public PlayerListDAO getAllPlayers() {
		List<Player> players = playerRespository.getAllPlayers();
		return new PlayerListDAO(players);
	}
	
	@GetMapping("/players/id")
	public PlayerListDAO getPlayerWithId(@RequestParam int userId) {
		List<Player> players = playerRespository.getPlayerWithId(userId);
		if(players == null) {
			players = new ArrayList<Player>();
		}
		return new PlayerListDAO(players);
	}
	
	@GetMapping("/players/race")
	public PlayerListDAO getRaceParticipants(@RequestParam int raceId) {
		List<Player> players = playerRespository.getRaceParticipants(raceId);
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
}
