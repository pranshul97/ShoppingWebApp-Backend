//-------------------RetailerService by bhavya------------------------------------------------------------------------------------------//
package com.project.service;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

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
	
	public static String getHashedString(String text) {
		try {
			text = Base64.getEncoder().encodeToString(text.getBytes());

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());

			byte[] digest = md.digest();
			text = DatatypeConverter.printHexBinary(digest).toUpperCase();

			return text;

		} catch (Exception e) {
			return Base64.getEncoder().encodeToString(text.getBytes());
		}
	}

	
}
