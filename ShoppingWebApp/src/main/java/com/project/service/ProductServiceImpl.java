package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Product;
import com.project.exception.ProductsException;
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
	
	public List<Product> fetchByName(String name){
			if(!prod.isProductPresent(name))
				throw new ProductsException("Sorry no such Product exists.");
			List<Product> list=prod.fetchByName(name);
			return list;
		
	}
}
