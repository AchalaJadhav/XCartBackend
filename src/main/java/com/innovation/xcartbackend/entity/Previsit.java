package com.innovation.xcartbackend.entity;

import javax.persistence.*;

@Entity
public class Previsit {

	@Id
	private int previsitId;

	private int productId;

	private int userId;

//	@OneToOne
//	private Product product;
//	
//	@OneToOne
//	private User user;

	public int getPrevisitId() {
		return previsitId;
	}

	public void setPrevisitId(int previsitId) {
		this.previsitId = previsitId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
