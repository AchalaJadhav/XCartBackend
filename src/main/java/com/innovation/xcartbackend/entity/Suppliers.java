package com.innovation.xcartbackend.entity;

import javax.persistence.*;

@Entity
public class Suppliers {

	@Id
	private int supplierId;

	private String supplierName;

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

}
