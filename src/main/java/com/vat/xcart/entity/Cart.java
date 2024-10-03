package com.vat.xcart.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cart")
public class Cart {

    @Id
    private String id;

    //	@Column(name = "item_quantity")
    private int itemQuantity;

    //	@OneToOne
//	@JoinColumn(name = "user_id")
    private User user;

//	@ManyToMany(targetEntity = Product.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private List<Product> productList = new ArrayList<Product>();

    //	@OneToOne
//	@JoinColumn(name = "product_id")
    private Product product;

//	@OneToMany
//	@Column(name = "quantity")
//	private List<Integer> quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
