package com.project.repository;

import java.util.List;

import com.project.entity.Product;

public interface ProductRepo {

	Product fetchById(int id);
	
	boolean isProductPresent(String productName);
	
	List<Product> fetchByName(String productName);
}