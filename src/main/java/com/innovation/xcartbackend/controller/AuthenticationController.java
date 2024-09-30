package com.innovation.xcartbackend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.innovation.xcartbackend.configuration.CustomUserDetailService;
import com.innovation.xcartbackend.configuration.UserAuthRequest;
import com.innovation.xcartbackend.configuration.JwtTokenHelper;
import com.innovation.xcartbackend.dto.UserResponseObject;
import com.innovation.xcartbackend.entity.User;
import com.innovation.xcartbackend.repository.UserRespository;
import com.innovation.xcartbackend.response.LoginResponse;
import com.innovation.xcartbackend.response.UserResponse;
import com.innovation.xcartbackend.service.AuthenticationService;
import com.innovation.xcartbackend.service.UserService;

@RestController
@CrossOrigin("http://localhost:4200")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping(path = "/login")
	public UserResponse generateToken(@RequestBody UserAuthRequest userAuthRequest) throws Exception {
		return authenticationService.loginUser(userAuthRequest);
	}

	@PostMapping(path = "/signup")
	public UserResponse signUpUser(@RequestBody @Valid User user) {
		return authenticationService.signUpUser(user);
	}
	
	@PostMapping(path = "/validateUsername")
	public UserResponse validateUsername(@RequestBody @Valid User user) 
	{
		return authenticationService.validateUsername(user);
	}
	
	@PostMapping(path = "/updatePasswordAndSecurity")
	public UserResponse updatePasswordAndSecurity(@RequestBody User user) {
		return authenticationService.updatePasswordAndSecurity(user);
	}

}
