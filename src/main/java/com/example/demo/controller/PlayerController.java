package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PlayerListDAO;
import com.example.demo.model.Player;
import com.example.demo.respository.PlayerRespository;

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
}
