package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Race;

public class RacesListDAO {
	
	private List<Race> races;
	
	public RacesListDAO(List<Race> races) {
		super();
		this.races = races;
	}

	public List<Race> getRaces() {
		return races;
	}

	public void setRaces(List<Race> races) {
		this.races = races;
	}
	
	

//	public class Result {
//		private int raceId;
//		private Race race;
//		public int getRaceId() {
//			return raceId;
//		}
//		public void setRaceId(int raceId) {
//			this.raceId = raceId;
//		}
//		public Race getRace() {
//			return race;
//		}
//		public void setRace(Race race) {
//			this.race = race;
//		}
//	}
    
}
