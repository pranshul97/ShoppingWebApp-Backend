package com.project.Dto;

public class CategoryDto {
	
	public double minPrice;
	public double maxPrice;
	public String[] catName;
	public String[] brandName;
	
	
	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String[] getCatName() {
		return catName;
	}

	public void setCatName(String[] catName) {
		this.catName = catName;
	}

	public String[] getBrandName() {
		return brandName;
	}

	public void setBrandName(String[] brandName) {
		this.brandName = brandName;
	}

	public int sizeOfArray(String[] name) {
		return name.length;
	}

}
