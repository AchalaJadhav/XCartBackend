package com.vat.xcart.controller;

import com.vat.xcart.entity.Product;
import com.vat.xcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductController
{

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public List<Product> addProduct(@RequestBody List<Product> itemModel){
        return productService.addItem(itemModel);
    }

    @GetMapping("/getAll")
    public List<Product> getAllProduct(){
        return productService.getAllItem();
    }

    @GetMapping("/getById")
    public Product getProductById(String id){
        return productService.getItemById(id);
    }

    @GetMapping(path = "/getproducts")
    public ResponseEntity<List<Product>> getProducts() {
        return productService.getProductsFromDB();
    }

    @GetMapping(path = "/getcategories")
    public ResponseEntity<String> getCategories() {
        return productService.getProductCategoriesFromDB();
    }

    @PostMapping(path = "addproduct")
    public ResponseEntity<String> addProducts(@RequestBody List<Product> products) {
        return productService.addProducts(products);
    }

}
