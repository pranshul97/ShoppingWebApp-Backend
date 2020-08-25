package com.project.repository;

import java.util.List;

import com.project.entity.Product;

public interface CompareRepo {

	Product fetchProductById(int id);
	
	boolean isCategoryPresent(String catName);
	
	int fetchCategoryIdByName(String catName);
	
	Product fetchproductByCategoryName(int catId);
	
	boolean isBrandPresent(String brandName);
	
	List<Integer> fetchProductIdByBrandName(String brandName);
	
	Product fetchProductByBrandName(int pId);
	
	List<Product> fetchProductWithMinPrice(int minPrice);
	
	List<Product> fetchProductWithMaxPrice(int maxPrice);
	
	List<Product> fetchProductInPriceRange(int minPrice, int maxPrice);

}