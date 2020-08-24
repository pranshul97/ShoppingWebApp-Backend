package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Product;
import com.project.repository.ProductRepo;

//-------------created and managed by Pranshul-----------------
@Service
public class ProductServiceImpl implements ProductService {

	
	
	@Autowired
	private ProductRepo prod;
	
	
	@Override
	public Product fetchProduct(int id) {
		return prod.fetchById(id);
	}
}
