package com.example.demo.controller;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.RacesListDAO;
import com.example.demo.model.Race;
import com.example.demo.respository.HostingRespository;
import com.example.demo.respository.RacesRespository;
import com.google.gson.Gson;

@RestController
public class RacesController {
	@Autowired
	RacesRespository racesRespository;
	 
	@Autowired
	HostingRespository hostRespository;
	
	@GetMapping("/races")
	public RacesListDAO getAllRaces() {
		List<Race> races = racesRespository.getAllRaces();
		if(races == null) {
			races = new ArrayList<>();
		}
		return new RacesListDAO(races);
	}
	
	@GetMapping("/races/id")
	public Race getRaceById(@RequestParam int id) {
		Race race = racesRespository.getRaceById(id);
		if(race == null) {
			race = new Race();
		}
		return race;
	}
	
	@GetMapping("/races/distance")
	public RacesListDAO getRacesWithDistanceRange(@RequestParam double from, @RequestParam double to) {
		List<Race> races = racesRespository.getRacesWithDistanceRange(from, to);
		return new RacesListDAO(races);
	}
	
	@RequestMapping(value = "/races/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody RacesListDAO searchRacesWithName(String name) {
		Gson gson = new Gson();
		Race race = gson.fromJson(name, Race.class);
		List<Race> races = racesRespository.getRacesWithName(race.getName());
		return new RacesListDAO(races);
	}
	
	@GetMapping("/races/ongoing")
	public RacesListDAO getOngoingRaces() {
		List<Race> races = racesRespository.getOngoingRaces();
		if(races == null) {
			races = new ArrayList<Race>();
		}
		return new RacesListDAO(races);
	}
	
	@GetMapping("/races/ended/all")
	public RacesListDAO getEndedRaces() {
		List<Race> races = racesRespository.getAllEndedRaces();
		if(races == null) {
			races = new ArrayList<Race>();
		}
		return new RacesListDAO(races);
	}
	
	@RequestMapping(value = "/races/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody Race createRace(String raceJson1, String userId) {
		Gson gson = new Gson();
		Race race = gson.fromJson(raceJson1, Race.class);
		String imageString = race.getRaceImage();
		race.setRaceImage("");
		racesRespository.save(race);
		hostRespository.addHosting(Integer.valueOf(userId), race.getRaceId());
		//racesRespository.addRace(race.getStartTime(), race.getEndTime(), race.getName(), race.getDistance(), race.getRegulation(), race.getDescription() , race.getRacePassword());
		//hostRespository.addHosting(Integer.valueOf(userId), Integer.valueOf(racesRespository.getLastInserted()));
		try{
			String imageName = "Race" + race.getRaceId() + ".jpg";
			FileOutputStream fos = new FileOutputStream("C:\\Users\\quynh\\eclipse-workspace\\FirstRestFulService\\image_race\\" + imageName);
			//FileOutputStream fos = new FileOutputStream("image_race\\" + imageName);
			byte byteArray[] = Base64.decodeBase64(imageString);
			fos.write(byteArray);
			fos.close(); 
			//racesRespository.editRaceImage("https://virtualrace.herokuapp.com/image_race/" + imageName, race.getRaceId());
			racesRespository.editRaceImage("http://192.168.43.195:8080/image_race/" + imageName, race.getRaceId());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return race;
	}


	@RequestMapping(value = "/races/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody Race editRaceInfo(String raceJson) {
		Gson gson = new Gson();
		Race race = gson.fromJson(raceJson, Race.class);
		if(race != null) {
			racesRespository.editRaceInfo(race.getStartTime(), race.getEndTime(), race.getName(), 
					race.getDistance(), race.getRegulation(), race.getDescription(), race.getRaceId());
		}else {
			race = new Race();
		}
		return race;
	}
}
