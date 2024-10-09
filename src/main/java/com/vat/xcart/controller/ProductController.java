package com.vat.xcart.controller;

import com.vat.xcart.models.entity.Product;
import com.vat.xcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController
{

    @Autowired
    ProductService productService;


    @GetMapping("/getProduct")
    public ResponseEntity<?> getProduct(@RequestParam String productId) {
        try {
            Optional<Product> product = productService.getProductByProductId(productId);
            return product.isPresent()
                    ? ResponseEntity.ok(product.get())
                    : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found with productId: " + productId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching product: " + e.getMessage());
        }
    }


    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No products available");
            }
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching products: " + e.getMessage());
        }
    }
    //ADMIN
    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(productService.addProducts(Collections.singletonList(product)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid product data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding product: " + e.getMessage());
        }
    }

    @PostMapping(path = "/addProducts")
    public ResponseEntity<String> addProducts(@RequestBody List<Product> products) {
        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProducts(products));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid product data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding products: " + e.getMessage());
        }
    }
}


