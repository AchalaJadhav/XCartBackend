package com.vat.xcart.models.dto.response;

import com.vat.xcart.models.entity.Product;
import com.vat.xcart.models.pojo.CartItem;
import com.vat.xcart.models.pojo.CartProduct;
import lombok.Data;

import java.util.List;

@Data
public class CartResponse {

    private String id;
    private String userId; // Reference to the User's ID
    private List<CartProduct> items;

    public CartResponse(String id, String userId, List<CartProduct> items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
    }

    public CartResponse() {
    }
}
