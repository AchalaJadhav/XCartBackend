package com.innovation.xcartbackend.setbaserecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.innovation.xcartbackend.configuration.CustomUserDetailService;
import com.innovation.xcartbackend.entity.User;
import com.innovation.xcartbackend.repository.UserRespository;
import com.innovation.xcartbackend.service.QuestionService;
import com.innovation.xcartbackend.service.UserService;
import com.innovation.xcartbackend.util.Role;

@Component
public class DefaultUsers 
{
	@Autowired
	UserRespository userRespository;
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void AddUserSet1()
	{
		String que = new QuestionService().getQuestions().get(1);
		if (userRespository.count() == 0) 
		{
		
			User user = new User();
			user.setUsername("vaibhav@gmail.com");
			user.setFirstName("Vaibhav");
			user.setLastName("Thakare");
			user.setGender("Male");
			user.setStatus("Active");
			user.setQuestion(que);
			user.setAnswer("vaibhav");
			user.setDateOfBirth("1994-03-29");
			user.setMobileNumber(7385374504L);
			//Directly using repo for Admin role 
			user.setPassword(this.passwordEncoder.encode("vaibhav"));
			user.setRole(Role.ROLE_ADMIN.name());
			userRespository.save(user);
			

			User user2 = new User();
			user2.setUsername("suyash@gmail.com");
			user2.setFirstName("Suyash");
			user2.setLastName("Dixit");
			user2.setGender("Male");
			user2.setPassword("suyash");
			user2.setQuestion(que);
			user2.setAnswer("suyash");
			user2.setDateOfBirth("1999-05-02");
			user2.setMobileNumber(7385374504L);
			customUserDetailService.signUpUser(user2);
			
			User user3 = new User();
			user3.setUsername("shreya@gmail.com");
			user3.setFirstName("Shreya");
			user3.setLastName("Rathor");
			user3.setGender("Female");
			user3.setPassword("shreya");
			user3.setQuestion(que);
			user3.setAnswer("shreya");
			user3.setDateOfBirth("1999-04-22");
			user3.setMobileNumber(7845123695L);
			customUserDetailService.signUpUser(user3);
			
			User user4 = new User();
			user4.setUsername("ajinkya200@gmail.com");
			user4.setFirstName("Ajinkya");
			user4.setLastName("Deshpande");
			user4.setGender("Male");
			user4.setPassword("ajinkya");
			user4.setQuestion(que);
			user4.setAnswer("ajinkya");
			user4.setDateOfBirth("1998-09-10");
			user4.setMobileNumber(1234567890L);
			customUserDetailService.signUpUser(user4);
			
			System.out.println("UserSet1 Added");
			
		} else 
		{
			System.out.println("UserSet1 Already Added");
		}
	}

}
