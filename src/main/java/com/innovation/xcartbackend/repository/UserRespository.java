package com.innovation.xcartbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovation.xcartbackend.entity.User;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {

//	boolean existsByEmail(String email);

	// Optional<User> findByEmail(String email);
	
	User findByUsername(String username);

	boolean existsByUsername(String username);
	
//	String findByUsername(String username);
}
