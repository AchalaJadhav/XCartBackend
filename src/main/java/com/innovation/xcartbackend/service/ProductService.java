package com.innovation.xcartbackend.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.innovation.xcartbackend.entity.Product;
import com.innovation.xcartbackend.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public ResponseEntity<List<Product>> getProductsFromDB() {
		List<Product> products = productRepository.findAll();
		System.out.println("Product id : " + products.get(0).getProductId());
		System.out.println(productRepository.count());
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}

	public ResponseEntity<List<String>> getProductCategoriesFromDB() {
		
		List<Product> products = productRepository.findAll();
		
		List<String> categories =  productRepository.getCategories();
		System.out.println("Product id : " + products.get(0).getProductId());
		System.out.println(productRepository.count());
		
		return ResponseEntity.status(HttpStatus.OK).body(categories);
	}
	
	public ResponseEntity<String> addProducts(List<Product> products) {
		productRepository.saveAll(products);
		return ResponseEntity.status(HttpStatus.OK).body("Product Saved");
	}
}
