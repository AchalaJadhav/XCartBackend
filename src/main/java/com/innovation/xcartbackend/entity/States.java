package com.innovation.xcartbackend.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class States {

	@Id
	private int stateId;

	private String stateName;

//	@OneToMany
//	private List<City> city;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

}
