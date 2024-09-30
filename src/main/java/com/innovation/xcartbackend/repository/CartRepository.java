package com.innovation.xcartbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovation.xcartbackend.entity.Cart;
import com.innovation.xcartbackend.entity.Product;
import com.innovation.xcartbackend.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	public boolean existsByUserAndProduct(Integer user, Integer product);

	public Cart findByUserAndProduct(Integer user, Integer product);

	public boolean existsByUserAndProduct(User user, Product product);

	public Cart findByUserAndProduct(User user, Product product);
	
	public List<Cart> findAllByUser(User user);
}
