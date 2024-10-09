package com.vat.xcart.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vat.xcart.models.entity.Address;
import com.vat.xcart.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AddressIngestionService {

    @Autowired
    private AddressRepository addressRepository;

    //Admin
    public void ingestData(List<Address> address) {
        // Ensure that the incoming address list does not contain duplicate _id
        addressRepository.saveAll(address);
    }

    }

