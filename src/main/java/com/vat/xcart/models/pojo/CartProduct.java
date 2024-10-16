package com.vat.xcart.models.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartProduct
{
    private String id;

    private String productName;

    private float price;

    private String productShortDescription;

    private int stock;

    private int productDiscount;

    private String path;

    private String category;

    private String tagLine;

    private String status;

    private int quantity;
}
