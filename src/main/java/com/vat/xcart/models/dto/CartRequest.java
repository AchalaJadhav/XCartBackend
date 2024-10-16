package com.vat.xcart.models.dto;

import lombok.Data;

@Data
public class CartRequest {

       private String userId;
       private String productId;
       private int quantity;


}
