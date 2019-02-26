package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.RacesListDAO;
import com.example.demo.model.Race;
import com.example.demo.respository.RacesRespository;

@RestController
public class RacesController {
	@Autowired
	RacesRespository racesRespository;
	
	@GetMapping("/races")
	public RacesListDAO getAllRaces() {
		List<Race> races = racesRespository.getAllRaces();
		return new RacesListDAO(races);
	}
	
	@GetMapping("/races/distance")
	public RacesListDAO getRacesWithDistanceRange(@RequestParam double from, @RequestParam double to) {
		List<Race> races = racesRespository.getRacesWithDistanceRange(from, to);
		return new RacesListDAO(races);
	}
}
