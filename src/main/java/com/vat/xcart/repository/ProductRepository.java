package com.vat.xcart.repository;

import com.vat.xcart.models.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    boolean existsByProductName (String productName);

}
