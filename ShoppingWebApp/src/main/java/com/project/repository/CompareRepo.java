package com.project.repository;

import java.util.List;

import com.project.entity.Product;

public interface CompareRepo {

	Product fetchProductById(int id);
	
	boolean isCategoryPresent(String catName);
	
	int fetchCategoryIdByName(String catName);
	
	List<Product> fetchproductByCategoryName(int catId);
	
	boolean isBrandPresent(String brandName);
	
	List<Integer> fetchProductIdByBrandName(String brandName);
	
	Product fetchProductByBrandName(int pId);
	
	List<Product> fetchProductWithMinPrice(double minPrice);
	
	List<Product> fetchProductWithMaxPrice(double maxPrice);
	
	List<Product> fetchProductInPriceRange(double minPrice, double maxPrice);

}