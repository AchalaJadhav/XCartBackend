package com.vat.xcart.models.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // No-argument constructor
@AllArgsConstructor  // All-argument constructor
public class CartItem {
    private String productId;
    private int quantity;
}