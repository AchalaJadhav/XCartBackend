package com.vat.xcart.repository;

import com.vat.xcart.models.entity.UserAddress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAddressRepository extends MongoRepository<UserAddress, Integer> {
    Optional<UserAddress> findByUserId(int userId);
}
