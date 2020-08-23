//-------------------RetailerService by bhavya------------------------------------------------------------------------------------------
package com.project.service;

import com.project.entity.Retailers;


public interface RetailerService {
	
	
	void addRetailer(Retailers retailers);
	
	Retailers login(String email, String password);
	
	

}
