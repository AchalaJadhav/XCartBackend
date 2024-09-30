package com.innovation.xcartbackend.configuration;

import java.util.Objects;

/**
 * This class contains username and password. The object of this class is used as a parameter in the generateToken method in AuthController.
 * @author Ajinkya.Deshpande
 *
 */
public class UserAuthRequest {
	
	private String username;
	
	private String password;

	/**
	 * 
	 * @return username type String
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username type String
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return password type String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password type String 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	


}
