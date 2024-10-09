package com.vat.xcart.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data  // Lombok annotation for @Getter, @Setter, @ToString, @EqualsAndHashCode
@Builder  // Lombok annotation for builder pattern
@NoArgsConstructor  // No-argument constructor
@AllArgsConstructor  // All-argument constructor
@Document(collection = "product")
public class Product {

    @Id
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

}
