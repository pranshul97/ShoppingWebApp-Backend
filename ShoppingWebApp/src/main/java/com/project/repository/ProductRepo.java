package com.project.repository;

import java.util.List;

import com.project.entity.Cart;
import com.project.entity.Image;
import com.project.entity.Product;
import com.project.entity.User;

public interface ProductRepo {

	Product fetchById(int id);
	
	boolean isProductPresent(String productName);
	
	List<Product> fetchByName(String productName);
	
	List<String> fetchBrandsRep();
	
	User fetchByUserId(int id);
	
	boolean isProductPresentInCart(int userId, int productId);
	
	void saveProductToCart(Cart cart);
	
	boolean isImagePresent(int productId);
	
	List<Image> getImages(int productId);
}