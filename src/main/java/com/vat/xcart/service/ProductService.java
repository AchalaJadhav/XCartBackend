package com.vat.xcart.service;


import com.vat.xcart.entity.Product;
import com.vat.xcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
