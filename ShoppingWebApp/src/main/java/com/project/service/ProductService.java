package com.project.service;

import java.util.List;

import com.project.entity.Product;

//---------Created and Managed By Pranshul-------------------
public interface ProductService {

	Product fetchProduct(int id);
	List<Product> fetchByName(String name);
}