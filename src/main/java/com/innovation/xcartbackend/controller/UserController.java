package com.innovation.xcartbackend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.innovation.xcartbackend.dto.UserResponseObject;
import com.innovation.xcartbackend.entity.User;
import com.innovation.xcartbackend.response.UserResponse;
import com.innovation.xcartbackend.service.QuestionService;
import com.innovation.xcartbackend.service.UserService;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {

	@Autowired
	UserService userService;

	ResponseEntity responseEntity;

	@GetMapping(path = "/getusers")
	public ResponseEntity<UserResponse> getUsers() {

		UserResponse userResponse = userService.getUsers();
		ResponseEntity<UserResponse> resp;

		if (userResponse.isStatus()) {
			resp = new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		} else {
			resp = new ResponseEntity<UserResponse>(userResponse, HttpStatus.NO_CONTENT);
		}
		return resp;
//		return responseEntity.ok(null).status(HttpStatus.OK).body(userService.getUsers());
	}
	
	@GetMapping(path = "/getquestions")
	public ResponseEntity<List<String>> getCategories() {
		return ResponseEntity.status(HttpStatus.OK).body(new QuestionService().getQuestions());
	}
	
	@PostMapping(path = "/getUserQuestionAnswer")
	public UserResponse getUserQuestionAnswer(@RequestBody User user) 
	{
		return userService.getUserQuestionAnswer(user);
	}

//	@GetMapping(path = "/getusers/{id}")
//	public Optional<User> getUserById(@PathVariable int id) {
//		return 
//	}

//	@PostMapping("/login")
//    ResponseEntity<?> userLogin(@RequestBody final UserLoginBean userLogin) {
//        System.out.println("Request body : " + userLogin.toString());
//        if (userLogin.getUsername().equals("admin") & userLogin.getPassword().equals("admin")) {
//            return new ResponseEntity<>(new ResponseMessage("Login Successfully..!"), null, HttpStatus.OK);
//        }
//
// 
//
//        return new ResponseEntity<>("Invalid login credentials ", null, HttpStatus.UNAUTHORIZED);
//    }
//
// 
//
//    @PostMapping("/register")
//    ResponseEntity<?> userRegister(@RequestBody final UserRegistrationBean userRegistrationBean) {
//        if (userRegistrationBean.getPassword().equals(userRegistrationBean.getConfirmPassword())) {
//            System.out.println("User : " + userRegistrationBean.toString());
//            return new ResponseEntity<>(new ResponseMessage("User registered Successfully..!"), null, HttpStatus.OK);
//        }
//
// 
//
//        return new ResponseEntity<>("Invalid login user dtails ", null, HttpStatus.BAD_REQUEST);
//    }

}