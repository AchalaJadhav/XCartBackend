package com.innovation.xcartbackend.response;

import java.util.List;

import org.springframework.stereotype.Component;

import com.innovation.xcartbackend.dto.UserResponseObject;
import com.innovation.xcartbackend.entity.User;




@Component
public class UserResponse {

	private String responseMessage;
	
	private String userToken;
	
	private boolean status;
	
	private UserResponseObject userResponseObject;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public UserResponseObject getUserResponseObject() {
		return userResponseObject;
	}

	public void setUserResponseObject(UserResponseObject userResponseObject) {
		this.userResponseObject = userResponseObject;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	
	
	
}
