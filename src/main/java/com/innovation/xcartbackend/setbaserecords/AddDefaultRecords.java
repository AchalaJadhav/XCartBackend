package com.innovation.xcartbackend.setbaserecords;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.innovation.xcartbackend.configuration.ConfigProp;

@Component
public class AddDefaultRecords 
{
	@Autowired
	ConfigProp ConfigProp;
	
	@Autowired
	DefaultUsers defaultUsers;
	
	@Autowired
	DefaultProducts defaultProducts;
	
	@Autowired
	DefaultCarts defaultCarts;
	
	public void AddFirstRecods() 
	{
		defaultUsers.AddUserSet1();
		defaultProducts.AddProductSet1();
		defaultCarts.AddCartSet1();
		System.out.println("FirstRecod Added");
		System.out.println(ConfigProp.getUrl());
	}

}
