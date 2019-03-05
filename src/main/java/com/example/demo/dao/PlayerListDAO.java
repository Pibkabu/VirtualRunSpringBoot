package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Player;


public class PlayerListDAO {
	private List<Player> players;

	public PlayerListDAO(List<Player> players) {
		super();
		this.players = players;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	
}
