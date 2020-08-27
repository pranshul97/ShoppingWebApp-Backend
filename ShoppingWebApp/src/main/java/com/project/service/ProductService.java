package com.project.service;

import java.util.List;

import com.project.entity.Cart;
import com.project.entity.Image;
import com.project.entity.Product;
import com.project.entity.User;

//---------Created and Managed By Pranshul-------------------
public interface ProductService {

	Product fetchProduct(int id);
	List<Product> fetchByName(String name);
	
	List<String> fetchBrands();
	
	User fetchUser(int id);
	
	void addProductToCart(Cart cart);
	
	List<Image> getImagesOfProduct(int productId);
}