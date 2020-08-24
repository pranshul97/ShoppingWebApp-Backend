//------------managed by bhavya-----------------------------------------//
package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Product;
import com.project.repository.ProductByRetailerRepository;


@Service
public class ProductByRetailerServiceImpl implements ProductByRetailerService {
	
	@Autowired
	private ProductByRetailerRepository productByRetailerService;

	@Override
	public void addProductByRetailer(Product product) {
		productByRetailerService.addProductByRetailer(product);
		
	}
	

}
