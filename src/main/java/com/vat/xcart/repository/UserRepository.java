package com.vat.xcart.repository;

import com.vat.xcart.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Custom query method to find a user by username
    User findByUsername(String username);
}
