package com.innovation.xcartbackend.entity;

import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.Columns;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;

	@Column(name = "item_quantity")
	private int itemQuantity;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

//	@ManyToMany(targetEntity = Product.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private List<Product> productList = new ArrayList<Product>();

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

//	@OneToMany
//	@Column(name = "quantity")
//	private List<Integer> quantity;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
