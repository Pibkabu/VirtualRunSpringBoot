package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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
import com.example.demo.model.UserAndRaceMaped;
import com.example.demo.model.UserHost;
import com.example.demo.respository.DonateAccountRepositpry;
import com.example.demo.respository.HostingRespository;
import com.example.demo.respository.RacesRespository;

@RestController
public class HostingController {
	
	@Autowired
	HostingRespository hostingRespository;
	
	@Autowired
	RacesRespository racesRespository;
	
	@Autowired
	DonateAccountRepositpry accountRepository;
	
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
			Collections.reverse(races);
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
			Collections.reverse(races);
		}
		return new RacesListDAO(races);
	}
	
	@RequestMapping(value = "/hosting/cancel", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody UserHost cancelHosting(String raceId) {
		int id = Integer.valueOf(raceId);
		hostingRespository.deleteParticipants(id);
		hostingRespository.deleteHosting(id);
		accountRepository.deleteDonateAccount(id);
		hostingRespository.deleteRace(id);
		UserHost host = new UserHost();
		host.setUserAndRaceMaped(new UserAndRaceMaped());
		return host;
	}
}
