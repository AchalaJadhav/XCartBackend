package com.innovation.xcartbackend.entity;

import javax.persistence.*;

@Entity
public class ProductDescription {

	@Id
	private int descriptionId;

	private int productId;

	private String productSubCategory1;

	private String productGenderType;

	private String productBrand;

	private String productSubCategory2;

	private String productDescription;

//	@OneToOne
//	private Product product;

	public int getDescriptionId() {
		return descriptionId;
	}

	public void setDescriptionId(int descriptionId) {
		this.descriptionId = descriptionId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductSubCategory1() {
		return productSubCategory1;
	}

	public void setProductSubCategory1(String productSubCategory1) {
		this.productSubCategory1 = productSubCategory1;
	}

	public String getProductGenderType() {
		return productGenderType;
	}

	public void setProductGenderType(String productGenderType) {
		this.productGenderType = productGenderType;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getProductSubCategory2() {
		return productSubCategory2;
	}

	public void setProductSubCategory2(String productSubCategory2) {
		this.productSubCategory2 = productSubCategory2;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

}
