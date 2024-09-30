package com.vat.xcart.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "city")
public class City {

	@Id
	private String id;

	private int stateId;

	private String cityName;

//	@ManyToOne
//	private States state;

	public String getCityId() {
		return id;
	}

	public void setCityId(String id) {
		this.id = id;
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
