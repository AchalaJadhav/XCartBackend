package com.innovation.xcartbackend.entity;

import javax.persistence.*;

@Entity
public class PlacedOrderAddress {

	@Id
	private int addresId;

	private String state;

	private String city;

	private String postalCode;

	private String addressLine;

	private int orderNumber;

//	@OneToOne
//	private OrderPlaced orderPlaced;

	public int getAddresId() {
		return addresId;
	}

	public void setAddresId(int addresId) {
		this.addresId = addresId;
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

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

}
