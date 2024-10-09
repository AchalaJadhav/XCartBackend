package com.vat.xcart.controller;

import com.vat.xcart.models.dto.CartRequest;
import com.vat.xcart.models.entity.Cart;
import com.vat.xcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Add products to the cart
    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@RequestBody CartRequest cartRequest) {
        try {
            Cart cart = cartService.addToCart(cartRequest.getUserId(), cartRequest.getProductId(), cartRequest.getQuantity());
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get all cart items for the user
    @GetMapping("/getCart")
    public ResponseEntity<?> getCartByUserId(@RequestBody CartRequest cartRequest) {
        try {
            Cart cart = cartService.getCartByUserId(cartRequest.getUserId());
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/removeFromCart")
    public ResponseEntity<?> removeFromCart(@RequestBody CartRequest cartRequest) {
        try {
            // Validate and remove item from cart
            Cart cart = cartService.removeFromCart(cartRequest.getUserId(), cartRequest.getProductId());

            return ResponseEntity.ok(cart);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while removing item from cart: " + e.getMessage());
        }
    }


    @PostMapping("/decreaseProductCount")
    public ResponseEntity<?> decreaseProductCount(@RequestBody CartRequest cartRequest) {
        try {
            // Decrease the product count from the cart
            Optional<Cart> updatedCart = cartService.decreaseProductCount(cartRequest.getUserId(), cartRequest.getProductId());

            if (updatedCart.isPresent()) {
                return ResponseEntity.ok(updatedCart);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart or product not found.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while decreasing product count: " + e.getMessage());
        }
    }

    @PostMapping("/increaseProductCount")
    public ResponseEntity<?> increaseProductCount(@RequestBody CartRequest cartRequest) {
        try {
            // Increase the product count from the cart
            Optional<Cart> updatedCart = cartService.increaseProductCount(cartRequest.getUserId(), cartRequest.getProductId());

            if (updatedCart.isPresent()) {
                return ResponseEntity.ok(updatedCart);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart or product not found.");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while increasing product count: " + e.getMessage());
        }
    }


}