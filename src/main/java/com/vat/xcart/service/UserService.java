package com.vat.xcart.service;

import com.vat.xcart.entity.User;
import com.vat.xcart.exception.ResourceNotFoundException;
import com.vat.xcart.exception.UserNotFoundException;
import com.vat.xcart.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User signup(@Valid User user) {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the user to the database
        user.setCreateDateTime(LocalDateTime.now());
        return userRepository.save(user);
    }

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

    public User authenticateUser(String username, String password) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Here, you should compare the stored hashed password with the input password
            // This example assumes plaintext comparison for simplicity
            if (user.getPassword().equals(passwordEncoder.encode(password))) {
                return user;
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new UserNotFoundException("User not found with username: " + username);
        }
    }
}
