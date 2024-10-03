package com.vat.xcart.controller;

import com.vat.xcart.dto.AuthenticationRequest;
import com.vat.xcart.entity.User;
import com.vat.xcart.securityconfig.jwt.CustomUserDetailsService;
import com.vat.xcart.securityconfig.jwt.XCartJwtUtil;
import com.vat.xcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private CustomUserDetailsService userAuthService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private XCartJwtUtil jwtUtil;

    // Endpoint to handle login and token generation
    @PostMapping("/login")
    public ResponseEntity<?> loginAndGenerateToken(@RequestBody AuthenticationRequest loginRequest) throws Exception {
        try {
            // Authenticate the user using their username and password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication failed");
        }

        // Load the user details from the database
        final UserDetails userDetails = userAuthService.loadUserByUsername(loginRequest.getUsername());

        // Generate the JWT token
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        // Return the JWT token wrapped in a ResponseEntity
        return ResponseEntity.ok().body(Collections.singletonMap("token", jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        // Check if the username already exists
        if (userService.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists.");
        }

        // Register the user
        userService.signup(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

}
