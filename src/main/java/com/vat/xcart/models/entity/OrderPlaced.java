package com.vat.xcart.models.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "order_placed")
public class OrderPlaced {

    @Id
    private String id;

    private String userId;

//	private int orderNumber;

    private String productId;

    private float price;

    private LocalDateTime orderDate;

    private int orderQuantity;

    private int totalAmount;

    private String orderStatus;

    private String modeOfPayment;

    private String address;


}
