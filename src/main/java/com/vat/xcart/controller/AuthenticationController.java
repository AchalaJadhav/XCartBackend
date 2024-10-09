package com.vat.xcart.controller;

import com.vat.xcart.exception.ResourceNotFoundException;
import com.vat.xcart.models.dto.AuthenticationRequest;
import com.vat.xcart.models.dto.jwt.CustomUserDetails;
import com.vat.xcart.models.entity.User;
import com.vat.xcart.service.jwtservice.CustomUserDetailsService;
import com.vat.xcart.securityconfig.jwt.XCartJwtUtil;
import com.vat.xcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
            // Check if the user exists before authentication
            CustomUserDetails userDetails = userAuthService.loadUserByUsername(loginRequest.getUsername());

            // Authenticate the user using their username and password
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // If authentication is successful, generate the token
            String jwt = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getId());

            return ResponseEntity.ok().body(Collections.singletonMap("token", jwt));

        }
        catch (UsernameNotFoundException e) {
            // User not registered scenario
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not registered");
        }
        catch (BadCredentialsException e) {
            // Incorrect password scenario
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication failed");
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        // Check if the username already exists
        if (userService.findByUsername(user.getUsername()) != null) {
            throw new ResourceNotFoundException("User is present");
        }

        // Register the user
        userAuthService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

}
