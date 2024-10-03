package com.vat.xcart.service;


import com.vat.xcart.entity.Product;
import com.vat.xcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //add new item
    public List<Product> addItem(List<Product> product){
        return productRepository.saveAll(product);
    }

    //get all item
    public List<Product> getAllItem(){
        return productRepository.findAll();
    }

    //get item by id
    public Product getItemById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public ResponseEntity<List<Product>> getProductsFromDB() {
        List<Product> products = productRepository.findAll();
        System.out.println("Product id : " + products.get(0).getProductId());
        System.out.println(productRepository.count());
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    public ResponseEntity<String> getProductCategoriesFromDB() {

        List<Product> products = productRepository.findAll();

        String categories =  productRepository.getCategories();
        System.out.println("Product id : " + products.get(0).getProductId());
        System.out.println(productRepository.count());

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    public ResponseEntity<String> addProducts(List<Product> products) {
        productRepository.saveAll(products);
        return ResponseEntity.status(HttpStatus.OK).body("Product Saved");
    }
}
