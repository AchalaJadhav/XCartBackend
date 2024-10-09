package com.vat.xcart.controller;

import com.vat.xcart.models.entity.Address;
import com.vat.xcart.repository.AddressRepository;
import com.vat.xcart.service.AddressIngestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressIngestionService addressIngestionService;

    @Autowired
    private AddressRepository addressRepository;

    //Admin
    // Endpoint to manually trigger the ingestion process
    @PostMapping("/ingest")
    public ResponseEntity<String> ingestData(@RequestBody List<Address> address) {
        try {
            addressIngestionService.ingestData(address);
            return ResponseEntity.ok("Data ingestion initiated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error during data ingestion: " + e.getMessage());
        }
    }

    // Endpoint to get all addresses
    @GetMapping("/all")
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return ResponseEntity.ok(addresses);
    }

}
