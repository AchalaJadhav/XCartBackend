package com.vat.xcart.service.jwtservice;

import com.vat.xcart.constant.Status;
import com.vat.xcart.models.dto.jwt.CustomUserDetails;
import com.vat.xcart.models.entity.User;
import com.vat.xcart.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

// Implementing the UserDetailsService interface
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    // Additional service methods such as user registration
    public User registerUser(User user) {
        // Hash the password before saving
        user.setPassword(encoder.encode(user.getPassword()));
        // Save the user to the database
        user.setCreateDateTime(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the repository
        User user = userRepository.findByUsername(username);

        // If user not found, throw an exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Create and return a UserDetails object
        return new CustomUserDetails(
                user.getId(), // Pass the user ID
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().name())), // Adjust if you have multiple roles
                true, // accountNonExpired
                true, // accountNonLocked
                true, // credentialsNonExpired
                user.getStatus() == Status.ACTIVE // Check if the account is enabled
        );
    }

}


