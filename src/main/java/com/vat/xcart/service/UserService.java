package com.vat.xcart.service;

import com.vat.xcart.entity.User;
import com.vat.xcart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

  //  private final BCryptPasswordEncoder passwordEncoder;

    public UserService() {
  //      this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User signup(@Valid User user) {
        // Hash the password before saving
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the user to the database
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
