package com.vat.xcart.service;

import com.vat.xcart.models.entity.UserAddress;
import com.vat.xcart.models.pojo.DetailAddress;
import com.vat.xcart.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressRepository userAddressRepository;

    public String addUserAddress(UserAddress userAddress) {
        // Check if nameType is unique within the same user
        Set<String> nameTypes = new HashSet<>();
        for (DetailAddress detail : userAddress.getUserAddresses()) {
            if (!nameTypes.add(detail.getAddresstype())) {
                throw new IllegalArgumentException("nameType '" + detail.getAddresstype() + "' must be unique within the user addresses.");
            }
        }

        // Save the user address
        userAddressRepository.save(userAddress);

        return "User address added successfully.";
    }
}



