package com.example.demo.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class UserAndRaceMaped implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int userId;
    private int raceId;
    
    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRaceId() {
		return raceId;
	}

	public void setRaceId(int raceId) {
		this.raceId = raceId;
	}

	@Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((userId == 0) ? 0 : String.valueOf(userId).hashCode());
      result = prime * result + ((raceId == 0) ? 0 : String.valueOf(raceId).hashCode());
      return result;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      UserAndRaceMaped other = (UserAndRaceMaped) obj;
      if (userId == 0) {
        if (other.userId != 0)
          return false;
      } else if (!Objects.equals(userId, other.userId))
        return false;
      if (raceId == 0) {
        if (other.raceId != 0)
          return false;
      } else if (!Objects.equals(raceId, other.raceId))
        return false;
      return true;
    }
}
