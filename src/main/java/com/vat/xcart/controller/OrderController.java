package com.vat.xcart.controller;

import com.vat.xcart.models.dto.CartRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController
{

    @PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(@RequestBody CartRequest cartRequest) {
return null;

    }
}
