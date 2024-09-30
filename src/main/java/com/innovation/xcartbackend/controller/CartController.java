package com.innovation.xcartbackend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.innovation.xcartbackend.configuration.JwtTokenHelper;
import com.innovation.xcartbackend.entity.Cart;
import com.innovation.xcartbackend.repository.UserRespository;
import com.innovation.xcartbackend.service.CartService;

@CrossOrigin("*")
@RestController
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	JwtTokenHelper jwtTokenHelper;

	@Autowired
	AuthenticationController authController;

	@Autowired
	UserRespository userRespository;

//	@GetMapping(path = "/getcart")
//	public List<Product> getCart(){
//		return cartService.getCart();
//	}

	@PostMapping(path = "/addtocart/{user}/{product}")
	public List<Cart> addToCart(@PathVariable long l, @PathVariable Integer product) {
			
		System.out.println("UserId: " + l + "Product Id: " + product);
		return cartService.addToCart(l, product);
	}

	@GetMapping(path = "/cart/{user}")
	public List<Cart> getCartByUser(@PathVariable Integer user) {
		return cartService.getCartDeatilsByUser(user);
	}

}