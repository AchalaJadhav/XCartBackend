package com.innovation.xcartbackend.configuration;


import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.innovation.xcartbackend.dto.UserResponseObject;
import com.innovation.xcartbackend.entity.User;
import com.innovation.xcartbackend.repository.CartRepository;
import com.innovation.xcartbackend.repository.UserRespository;
import com.innovation.xcartbackend.response.UserResponse;
import com.innovation.xcartbackend.service.UserService;
import com.innovation.xcartbackend.util.Role;

/**
 * This class implements UserDetailsService interface from
 * org.springframework.security.core.userdetails.UserDetailsService and is used
 * to operate on the user details.
 * 
 * @author Ajinkya.Deshpande
 *
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRespository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private CartRepository cartRepository;

	/**
	 * This method fetches the user data using the username.
	 * 
	 * @return Object of type UserDetails.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepo.findByUsername(username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAutherities(user));
	}

	/**
	 * Method responsible for saving valid user information in the user table.
	 * 
	 * @param user Object of type User
	 * @return Object of type User
	 */
	public UserResponse signUpUser(User user) {
		UserResponse userResponse = new UserResponse();
		
		user.setStatus("Active");
		user.setRole(Role.ROLE_USER.name());
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//If already present
		if(userRepo.findByUsername(user.getUsername()) != null)
		{
			userResponse.setStatus(false);
			userResponse.setResponseMessage("Username already registered");
		}
		else 
		{
			//If successfully saved
			if(userRepo.save(user).getUsername().equals(user.getUsername()))
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

	private Set getAutherities(User user) {

		Set autherities = new HashSet<>();
		String role = user.getRole();
		autherities.add(new SimpleGrantedAuthority("ROLE_"+role));
		return autherities;
	}

}
