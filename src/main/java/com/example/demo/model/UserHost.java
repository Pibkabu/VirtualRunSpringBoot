package com.example.demo.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserHost {
	@EmbeddedId
	UserAndRaceMaped userAndRaceMaped;

	public UserHost() {
	}

	public UserHost(UserAndRaceMaped userAndRaceMaped) {
		super();
		this.userAndRaceMaped = userAndRaceMaped;
	}

	public UserAndRaceMaped getUserAndRaceMaped() {
		return userAndRaceMaped;
	}

	public void setUserAndRaceMaped(UserAndRaceMaped userAndRaceMaped) {
		this.userAndRaceMaped = userAndRaceMaped;
	}
	
	
}
