//---------------Retailer Repository by bhavya------------------------------------------------------------------------------------------
package com.project.repository;

import com.project.entity.Retailers;

public interface RetailerRepository {
	void addRetailer(Retailers retailers);
	
	boolean isRetailerPresent(String email);
}
