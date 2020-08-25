//-------------managed by bhavya----------------------------------------//
package com.project.repository;

import com.project.entity.Category;
import com.project.entity.Image;
import com.project.entity.Product;
import com.project.entity.Retailers;

public interface ProductByRetailerRepository {
	
    boolean findByProductId(int id);
	
	void addProductByRetailer(Product product);
	
	Retailers fetchRetailerById(int id);
	
	boolean isCategoryPresent(String name);
	
	Category fetchCategory(String name);
	
	void addCategory(Category cat);
	
	boolean isProductPresent(int retailerId, String name);
	
	Product getProductByNameAndRetailerId(String name,int retailerId);
	
	void addImage(Image img);
}
