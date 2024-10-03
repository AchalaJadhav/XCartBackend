package com.vat.xcart.repository;

import com.vat.xcart.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProductRepository extends MongoRepository<Product, String> {
    // This will return the list of categories for all products
    @Query(value = "{}", fields = "{categories : 1}")
    String getCategories();
}
