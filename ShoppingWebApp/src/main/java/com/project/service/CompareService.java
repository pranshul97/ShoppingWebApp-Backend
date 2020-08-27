package com.project.service;

import java.util.List;

import com.project.entity.Category;
import com.project.entity.Product;
import com.project.entity.Retailers;

public interface CompareService {

	//Function to fetch product for comparison using pId
	Product fetchProductForCompare(int pId);

	//Function to fetch product to display by filters of cateogy wise using category name 
	List<Product> fetchProductForCategoryFilter(String catName);

	//Function to fetch product to display by filters using brand name
	List<Product> fetchProductForBrand(String brandName);

	//Function to fetch products for minimum price
	List<Product> fetchProductForMinPrice(double minPrice);

	//Function to fetch products for maximum price
	List<Product> fetchProductForMaxPrice(double maxPrice);

	//Function to fetch products for prince range
	List<Product> fetchProductForPriceRange(double minPrice, double maxPrice);

	//Function to fetch all products for admin
	List<Product> fetchAllProductforAdmin();

	//Function to fetch all retailers for admin
	List<Retailers> fetchAllRetailersforAdmin();

	//Function to fetch all categories for admin
	List<Category> fetchAllCategoriesforAdmin();
	
	//Function to fetch product present in cart for particular userId
	public List<Product> fetchProductForCart(int userId);

}