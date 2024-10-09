package com.vat.xcart.models.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Admin
//User
@Data
@Document(collection = "address")
public class Address {
    @Id
    private String id; // Change int to String to accommodate ObjectId

    private String pincode;
    private String officeName;
    private String taluka;
    private String city;
    private String state;
    private String country;

    // Constructor without id
    public Address(String pincode, String officeName, String taluka, String city, String state, String country) {
        this.pincode = pincode;
        this.officeName = officeName;
        this.taluka = taluka;
        this.city = city;
        this.state = state;
        this.country = country;
    }
}
