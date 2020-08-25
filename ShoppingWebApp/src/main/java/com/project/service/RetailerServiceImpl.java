//-----------------RetailerServiceImpl by bhavya---------------------------------------------------------------------------------------
package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.entity.Category;
import com.project.entity.Image;
import com.project.entity.Product;
import com.project.entity.Retailers;
import com.project.exception.RetailerServiceException;
import com.project.repository.ProductByRetailerRepository;
import com.project.repository.RetailerRepository;

@Service
public class RetailerServiceImpl implements RetailerService {
	
	@Autowired
	private RetailerRepository retailerRepository;
	
	@Autowired
	private ProductByRetailerRepository productByRetailerRepository;

	@Override
	public void addRetailer(Retailers retailers){
		
		if(!retailerRepository.isRetailerPresent(retailers.getEmail())) {
			retailerRepository.addRetailer(retailers);
		}
		else {
			throw new RetailerServiceException("Retailer Already Registered");
		}
				
	}

	@Override
	public Retailers login(String email, String password) {
		try {
			if(!retailerRepository.isRetailerPresent(email))
				throw new RetailerServiceException("Retailer needs to be registered");
			
			int retailerId =  retailerRepository.findByEmailAndPassword(email, password);
			Retailers retailers = retailerRepository.findById(retailerId);
			return retailers;
		}
		catch (EmptyResultDataAccessException e) {
			throw new RetailerServiceException("Incorrect username or password.Try again with different combination" );
		}
	}

	@Override
	public Retailers isProductPresent(String name, int retailerId) {
		if(!productByRetailerRepository.isProductPresent(retailerId, name)){
			return productByRetailerRepository.fetchRetailerById(retailerId);
			
		}
		else {
			throw new RetailerServiceException("Product already exist on this id");
		}
	}

	@Override
	public Category addCategory(String name) {
		if(!productByRetailerRepository.isCategoryPresent(name)){
			Category cat=new Category();
			cat.setCategoryName(name);
			productByRetailerRepository.addCategory(cat);
		}
		return productByRetailerRepository.fetchCategory(name);
	}

	@Override
	public void addProductByRetailer(Product product) {
		productByRetailerRepository.addProductByRetailer(product);
	}

	@Override
	public Product getProductBynameId(String name, int retailerId) {
		return productByRetailerRepository.getProductByNameAndRetailerId(name, retailerId);
	}

	@Override
	public void addProductWithImage(Image img) {
		productByRetailerRepository.addImage(img);
		
	}

	@Override
	public List<Category> fetchCategory() {
		return retailerRepository.fetchCategory();
	}
		
	

}
