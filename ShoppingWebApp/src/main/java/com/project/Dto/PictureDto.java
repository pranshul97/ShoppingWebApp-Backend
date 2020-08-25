package com.project.Dto;

import org.springframework.web.multipart.MultipartFile;

public class PictureDto {

	private String name;
	private double price;
	private String description;
	private String brandName;
	private String model;
	private int quantity;
	private int retailerId;
	private String categoryName;
	private MultipartFile productPic[];
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
	public int getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(int retailerId) {
		this.retailerId = retailerId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public MultipartFile[] getProductPic() {
		return productPic;
	}
	public void setProductPic(MultipartFile[] productPic) {
		this.productPic = productPic;
	}
	
	
}
