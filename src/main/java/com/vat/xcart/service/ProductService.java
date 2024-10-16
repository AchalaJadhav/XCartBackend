package com.vat.xcart.service;


import com.vat.xcart.models.entity.Product;
import com.vat.xcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getProductByProductId(String productId) {
        return productRepository.findById(productId);
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //Admin
    public String addProducts(List<Product> products) {
        List<Product> productsToAdd = new ArrayList<>();
        List<String> skippedProducts = new ArrayList<>();
        List<String> invalidProducts = new ArrayList<>();

        for (Product product : products) {
            // Check if product already exists by name
            if (productRepository.existsByProductName(product.getProductName())) {
                skippedProducts.add(product.getProductName() + " (already exists)");
            } else {
                // Check price and stock conditions
                if (product.getPrice() > 0 && product.getStock() > 0) {
                    productsToAdd.add(product);
                } else {
                    invalidProducts.add(product.getProductName() + " (invalid price/stock)");
                }
            }
        }

        if (productsToAdd.isEmpty()) {
            if (skippedProducts.size() == products.size()) {
                return "None of the products were added. These products already exist.";
            } else {
                return "None of the products were added. Some have invalid price/stock.";
            }
        } else {
            // Save valid products
            productRepository.saveAll(productsToAdd);

            StringBuilder resultMessage = new StringBuilder();
            resultMessage.append(productsToAdd.size()).append(" Products added successfully: ")
                    .append(productsToAdd.stream().map(Product::getProductName).collect(Collectors.joining(", "))).append("\n");

            if (!skippedProducts.isEmpty()) {
                resultMessage.append("Skipped products (already exist): ").append(String.join(", ", skippedProducts)).append("\n");
            }

            if (!invalidProducts.isEmpty()) {
                resultMessage.append("Skipped products (invalid price/stock): ").append(String.join(", ", invalidProducts)).append("\n");
            }

            return resultMessage.toString();
        }
    }

    public List<Product> getProductsByIds(List<String> productIds) {
        return productRepository.findByIdIn(productIds);
    }

}




