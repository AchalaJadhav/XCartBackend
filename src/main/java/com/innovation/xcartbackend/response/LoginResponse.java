package com.innovation.xcartbackend.response;

import org.springframework.stereotype.Component;

import com.innovation.xcartbackend.entity.User;

/**
 * Used to send user object and token to the frontend.
 * @author Ajinkya.Deshpande
 *
 */
@Component
public class LoginResponse {

	User loggedInUser = new User();

	String userToken;

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
	
}
