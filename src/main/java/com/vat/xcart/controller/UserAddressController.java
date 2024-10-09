package com.vat.xcart.controller;

import com.vat.xcart.models.entity.UserAddress;
import com.vat.xcart.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/useraddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @PostMapping("/add")
    public ResponseEntity<String> addUserAddress(@RequestBody UserAddress userAddress) {
        try {
            String response = userAddressService.addUserAddress(userAddress);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding user address: " + e.getMessage());
        }
    }

}
