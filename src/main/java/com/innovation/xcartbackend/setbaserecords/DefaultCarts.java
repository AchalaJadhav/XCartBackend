package com.innovation.xcartbackend.setbaserecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.innovation.xcartbackend.controller.CartController;
import com.innovation.xcartbackend.repository.CartRepository;
import com.innovation.xcartbackend.repository.ProductRepository;
import com.innovation.xcartbackend.repository.UserRespository;

@Component
public class DefaultCarts 
{
	@Autowired
	CartRepository cartRepository; 
	
	@Autowired
	ProductRepository  productRepository;
	
	@Autowired
	UserRespository userRespository;
	
	@Autowired
	CartController cartController;
	
	public void AddCartSet1()
	{
		if (productRepository.count() >= 3 && userRespository.count() >= 3 && cartRepository.count() == 0) 
		{
			
			cartController.addToCart(userRespository.findAll().get(1).getUserId(), productRepository.findAll().get(1).getProductId());
			cartController.addToCart(userRespository.findAll().get(1).getUserId(), productRepository.findAll().get(2).getProductId());
			cartController.addToCart(userRespository.findAll().get(1).getUserId(), productRepository.findAll().get(1).getProductId());
			
			cartController.addToCart(userRespository.findAll().get(2).getUserId(), productRepository.findAll().get(0).getProductId());
			cartController.addToCart(userRespository.findAll().get(2).getUserId(), productRepository.findAll().get(2).getProductId());
		
			System.out.println("CartSet1 Added");
		}
		else 
		{
			System.out.println("CartSet1 Is Already Added");
		}
	}

}
