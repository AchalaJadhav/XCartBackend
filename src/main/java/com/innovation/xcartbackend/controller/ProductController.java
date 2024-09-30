package com.innovation.xcartbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.innovation.xcartbackend.entity.Product;
import com.innovation.xcartbackend.service.ProductService;

@RestController
@CrossOrigin("http://localhost:4200")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping(path = "/getproducts")
	public ResponseEntity<List<Product>> getProducts() {
		return productService.getProductsFromDB();
	}

	@GetMapping(path = "/getcategories")
	public ResponseEntity<List<String>> getCategories() {
		return productService.getProductCategoriesFromDB();
	}
	
	@PostMapping(path = "addproduct")
	public ResponseEntity<String> addProducts(@RequestBody List<Product> products) {
		return productService.addProducts(products);
	}
}
