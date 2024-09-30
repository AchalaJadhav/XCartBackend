package com.innovation.xcartbackend.setbaserecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.innovation.xcartbackend.entity.Product;
import com.innovation.xcartbackend.repository.ProductRepository;

@Component
public class DefaultProducts 
{
	@Autowired
	ProductRepository productRepository; 
	
	public void AddProductSet1()
	{
		if (productRepository.count() == 0) 
		{
			Product product = new Product();
			product.setProductName("Apple iPhone 14");
			product.setCategory("Electronics");
			product.setProductShortDescription("Short Description of Apple iPhone 14");
			product.setPrice(129900);
			product.setProductDiscount(20);
			product.setPath("Path of Apple iPhone 14");
			product.setStatus("Available");
			product.setStock(50);
			product.setTagLine("TagLine of Apple iPhone 14");
			productRepository.save(product);

			Product product2 = new Product();
			product2.setProductName("Apple iPhone 12");
			product2.setCategory("Electronics");
			product2.setProductShortDescription("Short Description of Apple iPhone 12");
			product2.setPrice(90000);
			product2.setProductDiscount(20);
			product2.setPath("Path of Apple iPhone 12");
			product2.setStatus("Available");
			product2.setStock(40);
			product2.setTagLine("TagLine of Apple iPhone 12");
			productRepository.save(product2);
			
			Product product3 = new Product();
			product3.setProductName("Google Pixel 7");
			product3.setCategory("Electronics");
			product3.setProductShortDescription("Short Description of Google Pixel 7");
			product3.setPrice(95000);
			product3.setProductDiscount(30);
			product3.setPath("Path of Google Pixel 7");
			product3.setStatus("Available");
			product3.setStock(50);
			product3.setTagLine("TagLine of Apple Google Pixel 7");
			productRepository.save(product3);
			
			System.out.println("AddProductSet1 Added");
		}
		else 
		{
			System.out.println("AddProductSet1 Already Added");
		}
	}

}
