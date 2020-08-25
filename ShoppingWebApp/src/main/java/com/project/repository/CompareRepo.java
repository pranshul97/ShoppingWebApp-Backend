package com.project.repository;

import java.util.List;

import com.project.entity.Category;
import com.project.entity.Product;
import com.project.entity.Retailers;

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
	
	public boolean isProductPresntForAdmin();
	public List<Product> findAllProducts();
	
	public boolean isRetailerPresntForAdmin();
	public List<Retailers> findAllRetailers();

	public boolean isCategoryPresntForAdmin();
	public List<Category> findAllCategory();
}