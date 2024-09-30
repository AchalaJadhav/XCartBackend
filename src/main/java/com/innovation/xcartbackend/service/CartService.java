package com.innovation.xcartbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovation.xcartbackend.entity.Cart;
import com.innovation.xcartbackend.repository.CartRepository;
import com.innovation.xcartbackend.repository.ProductRepository;
import com.innovation.xcartbackend.repository.UserRespository;
import com.innovation.xcartbackend.response.LoginResponse;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	UserRespository userRespository;

	@Autowired
	LoginResponse loginResponse;

	@Autowired
	ProductRepository productRepository;

	public List<Cart> getFromCart() {
		return cartRepository.findAll();
	}

//	public List<Product> getCart() {
//		return userRespository.findById(loginResponse.getLoggedInUser().getUserId()).get().getCart().getProductList();
//	}

//	public String addToCart(int userId,int productId) {
//		int cartId =userRespository.findById(userId).get().getCart().getCartId();
//		Product product = productRepository.findById(productId).get();
//		cartRepository.findById(cartId).get().getProductList().add(product);
//		return "Product added";
//		
//	}
//	public List<Cart> addToCart(Integer user, Integer product) {
//
//		if (cartRepository.existsByUserAndProduct(user, product)) {
//			Cart existingCart = cartRepository.findByUserAndProduct(user, product);
//			existingCart.setItemQuantity(existingCart.getItemQuantity() + 1);
//			cartRepository.save(existingCart);
//		}
//		else {
//			Cart cart = new Cart();
//			cart.setProduct(productRepository.findById(product).get());
//			cart.setUser(userRespository.findById(user).get());
//			cart.setItemQuantity(1);
//			cartRepository.save(cart);
//
//		}
//		return cartRepository.findAll();
//
//	}

	public List<Cart> addToCart(long l, Integer product) {

		if (cartRepository.existsByUserAndProduct(userRespository.findById(l).get(),
				productRepository.findById(product).get())) {
			Cart existingCart = cartRepository.findByUserAndProduct(userRespository.findById(l).get(),
					productRepository.findById(product).get());
			existingCart.setItemQuantity(existingCart.getItemQuantity() + 1);
			cartRepository.save(existingCart);
		} else {
			Cart cart = new Cart();
			cart.setProduct(productRepository.findById(product).get());
			cart.setUser(userRespository.findById(l).get());
			cart.setItemQuantity(1);
			cartRepository.save(cart);

		}
		return cartRepository.findAllByUser(userRespository.findById(l).get());

	}

	/**
	 * @param user to identify record based on user
	 * @return card details of given user
	 */
	public List<Cart> getCartDeatilsByUser(final long user) {
		return cartRepository.findAllByUser(userRespository.findById(user).get());
	}
}
