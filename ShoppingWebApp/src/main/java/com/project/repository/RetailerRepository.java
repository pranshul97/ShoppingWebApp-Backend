//---------------Retailer Repository by bhavya------------------------------------------------------------------------------------------
package com.project.repository;

import java.util.List;

import com.project.entity.Product;
import com.project.entity.Retailers;

public interface RetailerRepository {
	void addRetailer(Retailers retailers);
	
	boolean isRetailerPresent(String email);
	
	List<Retailers> findAllRetailers();
	
	int findByEmailAndPassword(String email, String password);
	
	Retailers findById(int retailerId);
	
	Product addProductByRetailer(Product product);
}
