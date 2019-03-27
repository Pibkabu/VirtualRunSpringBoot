package com.example.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Race {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int raceId;
	
    private Timestamp createTime;
    
    private Timestamp startTime;
    
    private Timestamp endTime;
    
    @Column(name = "RaceName")
    private String name;
    
    private double distance;
    
    private String regulation;
    
    private String description;
    
    private int totalPlayer;
    
    private String raceImage;
    
	public Race() {
		
	}
	
	
	public Race(int raceId, Timestamp createTime, Timestamp startTime, Timestamp endTime, String name,
			double distance, String regulation, String description, int totalPlayer) {
		this.raceId = raceId;
		this.createTime = createTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.name = name;
		this.distance = distance;
		this.regulation = regulation;
		this.description = description;
		this.totalPlayer = totalPlayer;
	}
	
	public Race(int raceId, Timestamp createTime, Timestamp startTime, Timestamp endTime, String name, double distance,
			String regulation, String description, int totalPlayer, String raceImage) {
		super();
		this.raceId = raceId;
		this.createTime = createTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.name = name;
		this.distance = distance;
		this.regulation = regulation;
		this.description = description;
		this.totalPlayer = totalPlayer;
		this.raceImage = raceImage;
	}


	public int getRaceId() {
		return raceId;
	}
	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getRegulation() {
		return regulation;
	}
	public void setRegulation(String regulation) {
		this.regulation = regulation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTotalPlayer() {
		return totalPlayer;
	}
	public void setTotalPlayer(int totalPlayer) {
		this.totalPlayer = totalPlayer;
	}


	public String getRaceImage() {
		return raceImage;
	}


	public void setRaceImage(String raceImage) {
		this.raceImage = raceImage;
	}
    
	
    
}
