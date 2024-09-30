package com.innovation.xcartbackend.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Address {

	@Id
	@GeneratedValue
	private int addressId;

	private String state;

	private String city;

	private String postalCode;

	private String addressLine;

	private int userId;

	private int supplierId;

//	@ManyToOne
//	private Suppliers suppliers;
//
//	@OneToMany
//	private List<User> user;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

}
