package com.shopping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_products")
public class Product {

	@Id
	@GeneratedValue
	@Column(name="Product_Id")
	private int productId;
	
	@Column(name="Product_Name")
	private String name;
	
	@Column(name="Price")
	private double price;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="Brand_Name")
	private String brandName;
	
	@Column(name="Model")
	private String model;
	
	@Column(name="Quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="Retailer_Id")
	private Retailers retailer;//This is for resolving Many to many relationship
	//private int itemsSold;
	
	@ManyToOne
	@JoinColumn(name="Category_Id")
	private Category category;


	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Retailers getRetailer() {
		return retailer;
	}

	public void setRetailer(Retailers retailer) {
		this.retailer = retailer;
	}

	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
