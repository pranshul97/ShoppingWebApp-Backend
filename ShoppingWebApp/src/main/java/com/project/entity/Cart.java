package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_cart")
public class Cart {

	@Id
	@GeneratedValue
	@Column(name="Cart_Id")
	private int id;
	
	@ManyToOne//this can be made lazy.
	@JoinColumn(name="User_Id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="Product_Id")
	private Product product;
	@Column(name="Quantity")
	private int quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
