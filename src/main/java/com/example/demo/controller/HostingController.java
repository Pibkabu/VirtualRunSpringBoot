package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.RacesListDAO;
import com.example.demo.model.Race;
import com.example.demo.model.UserHost;
import com.example.demo.respository.HostingRespository;
import com.example.demo.respository.RacesRespository;

@RestController
public class HostingController {
	
	@Autowired
	HostingRespository hostingRespository;
	
	@Autowired
	RacesRespository racesRespository;
	
	@GetMapping("/hosting/ongoing")
	public RacesListDAO getOngoingRacesUserHosting(@RequestParam int userId) {
		List<UserHost> hosting = hostingRespository.getRacesUserHosting(userId);
		List<Race> races = new ArrayList<>();
		if(hosting != null) {
			for (UserHost userHost : hosting) {
				Race race = racesRespository.getRaceById(userHost.getUserAndRaceMaped().getRaceId());
				Calendar currentDate = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date raceEndTime =  format.parse(race.getEndTime().toString());
					if(raceEndTime.getTime() > currentDate.getTimeInMillis()) {
						races.add(race);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		return new RacesListDAO(races);
	}
	
	@GetMapping("/hosting/past")
	public RacesListDAO getPastRacesUserHosting(@RequestParam int userId) {
		List<UserHost> hosting = hostingRespository.getRacesUserHosting(userId);
		List<Race> races = new ArrayList<>();
		if(hosting != null) {
			for (UserHost userHost : hosting) {
				Race race = racesRespository.getRaceById(userHost.getUserAndRaceMaped().getRaceId());
				Calendar currentDate = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date raceEndTime =  format.parse(race.getEndTime().toString());
					if(raceEndTime.getTime() < currentDate.getTimeInMillis()) {
						races.add(race);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		return new RacesListDAO(races);
	}
}
