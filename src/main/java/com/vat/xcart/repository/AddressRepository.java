package com.vat.xcart.repository;

import com.vat.xcart.models.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//Admin
//Customer
@Repository
public interface AddressRepository extends MongoRepository<Address, Integer> {
}