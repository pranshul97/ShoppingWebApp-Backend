//-------------------RetailerService by bhavya------------------------------------------------------------------------------------------//
package com.project.service;

import java.util.List;

import com.project.entity.Category;
import com.project.entity.Image;
import com.project.entity.Product;
import com.project.entity.Retailers;


public interface RetailerService {
	
	
	void addRetailer(Retailers retailers);
	
	Retailers login(String email, String password);
	
	void addProductByRetailer(Product product);
	
	Category addCategory(String name);
	
	Retailers isProductPresent(String name, int retailerId);

	Product getProductBynameId(String name, int retailerId);

	void addProductWithImage(Image img);
	
	List<Category> fetchCategory();
}
