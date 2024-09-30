package com.innovation.xcartbackend.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.innovation.xcartbackend.configuration.CustomUserDetailService;
import com.innovation.xcartbackend.configuration.JwtTokenHelper;
import com.innovation.xcartbackend.configuration.UserAuthRequest;
import com.innovation.xcartbackend.dto.UserResponseObject;
import com.innovation.xcartbackend.entity.User;
import com.innovation.xcartbackend.repository.UserRespository;
import com.innovation.xcartbackend.response.LoginResponse;
import com.innovation.xcartbackend.response.UserResponse;
import com.innovation.xcartbackend.util.Role;

@Service
public class AuthenticationService 
{
	@Autowired
	private UserRespository userRespository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private CustomUserDetailService userDetailsService;

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	LoginResponse loginResponse;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	UserRespository userRepo;

	public static String tokenString;

	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}
	
	public UserResponse signUpUser(User user) {
		UserResponse userResponse = new UserResponse();	
		user.setStatus("Active");
		user.setRole(Role.ROLE_USER.name());
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//If already present
		if(userRespository.findByUsername(user.getUsername()) != null)
		{
			userResponse.setStatus(false);
			userResponse.setResponseMessage("Username already registered");
		}
		else 
		{
			//If successfully saved
			if(userRespository.save(user).getUsername().equals(user.getUsername()))
			{
				userResponse.setStatus(true);
				userResponse.setResponseMessage("Registered Successfully");
			}
			else 
			{
				userResponse.setStatus(false);
				userResponse.setResponseMessage("Error while saving");
			}
		}
		return userResponse;
	}
	
	public UserResponse loginUser(UserAuthRequest userAuthRequest) 
	{
		UserResponse userResponse = new UserResponse();
		User user = userRepo.findByUsername(userAuthRequest.getUsername());
		//If username exists
		if(user != null && user.getUsername().equals(userAuthRequest.getUsername()))
		{
			try 
			{
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(userAuthRequest.getUsername(), userAuthRequest.getPassword()));
				userResponse.setStatus(true);
				userResponse.setResponseMessage("Login Successfully");
				userResponse.setUserResponseObject(userService.userToDto(user));
				tokenString = jwtTokenHelper.generateToken(userAuthRequest.getUsername(),user.getRole());
				userResponse.setUserToken(tokenString);
			}
			//If username exists but password wrong
			catch (Exception ex) 
			{
				userResponse.setStatus(false);
				UserResponseObject userResponseObject =  new UserResponseObject();
				userResponseObject.setUserId(-1);
				userResponse.setUserResponseObject(userResponseObject);
				userResponse.setResponseMessage("Wrong password");
			}

		}
		//If username does not exists
		else 
		{
			userResponse.setStatus(false);
			UserResponseObject userResponseObject =  new UserResponseObject();
			userResponseObject.setUserId(-2);
			userResponse.setUserResponseObject(userResponseObject);
			userResponse.setResponseMessage("Not registered");
		}

		return userResponse;
	}

	public UserResponse validateUsername(@Valid User checkUser) 
	{
		UserResponse userResponse = new UserResponse();
		User user = userRepo.findByUsername(checkUser.getUsername());
		//If username exists
		if(user != null && user.getUsername().equals(checkUser.getUsername()))
		{
			userResponse.setStatus(true);
			UserResponseObject userResponseObject =  new UserResponseObject();
			userResponseObject.setUserId(user.getUserId());
			userResponse.setUserResponseObject(userResponseObject);
			userResponse.setResponseMessage("Username exists");
		}
		//If username does not exists
		else 
		{
			userResponse.setStatus(false);
			userResponse.setResponseMessage("Username doest not exists");
		}

		return userResponse;
	}

	public UserResponse updatePasswordAndSecurity(@Valid User checkUser) {
	
		UserResponse userResponse = new UserResponse();
		User user = userRepo.findByUsername(checkUser.getUsername());
		//If username exists
		if(user != null && user.getUsername().equals(checkUser.getUsername()))
		{
			
			user.setQuestion(checkUser.getQuestion());
			user.setAnswer(checkUser.getAnswer());
			user.setPassword(this.passwordEncoder.encode(checkUser.getPassword()));
			User savedUser = userRepo.save(user);
			if( savedUser != null && savedUser.getUsername().equals(checkUser.getUsername()))
			{
				userResponse.setStatus(true);
				UserResponseObject userResponseObject =  new UserResponseObject();
				userResponseObject.setUserId(savedUser.getUserId());
				userResponse.setUserResponseObject(userResponseObject);
				userResponse.setResponseMessage("Username exists");				
			}
			else 
			{
				userResponse.setStatus(false);
				userResponse.setResponseMessage("Not able to update details");
			}

		}
		//If username does not exists
		else 
		{
			userResponse.setStatus(false);
			userResponse.setResponseMessage("Username doest not exists");
		}

		return userResponse;
	}

}
