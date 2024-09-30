package com.innovation.xcartbackend.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import com.innovation.xcartbackend.dto.UserResponseObject;
import com.innovation.xcartbackend.entity.User;
import com.innovation.xcartbackend.repository.UserRespository;
import com.innovation.xcartbackend.response.UserResponse;

import io.jsonwebtoken.lang.Collections;
/**
 *This Class defines Business logic for User operations. 
 * @author Ajinkya.Deshpande.
 *
 */
@Service
public class UserService {

	@Autowired
	UserRespository userRespository;

	@Autowired
	ModelMapper modelMapper;
	
//	public User dtoToUser(UserDto userDto) {
//		User user = this.modelMapper.map(userDto, User.class);
//		return user;
//	}

	/**
	 * This Class Accepts an objects of User type, converts User type object into UserDto type and returns it.
	 * @param object of type User
	 * @return object of type userDto
	 */
	public UserResponseObject userToDto(User user) {
		UserResponseObject userDto = this.modelMapper.map(user, UserResponseObject.class);
		return userDto;
	}

	/**
	 * 
	 * @return - list of users as UserDto
	 */
	public UserResponse getUsers() {

		UserResponse userResponse = new UserResponse();
//		List<UserDto> userDtoList = new ArrayList<>();
//		if (userRespository.count() >0) {
//			userResponse.setStatus(true);
//			userResponse.setResponseMessage("Users Present");
//			List<User> userList = userRespository.findAll();
//			userDtoList = userList.stream().map(user -> userToDto(user)).collect(Collectors.toList());
//			
//			userResponse.setUserList(userDtoList);
//			 
//		} else {
//			userResponse.setStatus(false);
//			userResponse.setResponseMessage("Users not present");
//			userResponse.setUserList(userDtoList);
//		}
		return userResponse;
	}

	/**
	 * 
	 * @param id of type int
	 * @return a required user or null if user not present
	 */
	public Optional<User> getUserById(long id) {
		User noUser = new User();
		if (userRespository.existsById(id)) {
			return userRespository.findById(id);
		} else {
			return null;
		}
	}
//	public List<User> getUsers(){
//		if (userRespository.count() != 0) {
//			return userRespository.findAll();
//			}
//		else {
//			return null;
//		}
//	}

	public UserResponse getUserQuestionAnswer(@Valid User checkUser) {
		UserResponse userResponse = new UserResponse();
		User user = userRespository.getById(checkUser.getUserId());
		if (user != null && user.getUserId() == checkUser.getUserId()) 
		{
			userResponse.setStatus(true);
			UserResponseObject userResponseObject =  new UserResponseObject();
			userResponseObject.setQuestion(user.getQuestion());
			userResponseObject.setAnswer(user.getAnswer());
			userResponseObject.setUsername(user.getUsername());
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
}
