package com.vat.xcart.service;

import com.vat.xcart.models.entity.User;
import com.vat.xcart.exception.ResourceNotFoundException;
import com.vat.xcart.exception.UserNotFoundException;
import com.vat.xcart.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User updateUser(String id, User updatedUser) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Update the existing user details
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setGender(updatedUser.getGender());
        existingUser.setDateOfBirth(updatedUser.getDateOfBirth());
        existingUser.setStatus(updatedUser.getStatus());
        existingUser.setMobileNumber(updatedUser.getMobileNumber());

        // Update the updateDateTime
        existingUser.setUpdateDateTime(LocalDateTime.now());

        return userRepository.save(existingUser);
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
