package com.innovation.xcartbackend.entity;

import javax.persistence.*;

@Entity
public class City {

	@Id
	private int cityId;

	private int stateId;

	private String cityName;

//	@ManyToOne
//	private States state;

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
