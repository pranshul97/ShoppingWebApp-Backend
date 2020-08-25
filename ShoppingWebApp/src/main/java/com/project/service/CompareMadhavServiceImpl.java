//---------------------------------MADHAV compare sevice class--------------------------------------------------------


package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Product;
import com.project.exception.CompareServiceException;
import com.project.repository.CompareRepo;

@Service
public class CompareMadhavServiceImpl {
	
	@Autowired
	private CompareRepo compareRepo;
	
	public Product fetchProductForCompare(int pId) {
		Product prod = compareRepo.fetchProductById(pId);
		return prod;
	}
	
	public Product fetchProductForCategoryFilter(String catName) {
		if(compareRepo.isCategoryPresent(catName)) {
			int catId = compareRepo.fetchCategoryIdByName(catName);
			Product catProd = compareRepo.fetchproductByCategoryName(catId);
			return catProd;
		}
		else
			throw new CompareServiceException("Category not found");	
	}
	
	public List<Product> fetchProductForBrand(String brandName) {
		if(compareRepo.isBrandPresent(brandName)) {
			List<Product> brandProduct = new ArrayList<>();
			List<Integer> pIdList = compareRepo.fetchProductIdByBrandName(brandName);
			for(int i=0; i<pIdList.size(); i++) {
				brandProduct.add(compareRepo.fetchProductByBrandName(i));
			}
			return brandProduct;		
		}
		else
			throw new CompareServiceException("Brand not found");
	}
	
	public List<Product> fetchProductForMinPrice(int minPrice){
		List<Product> priceProd = compareRepo.fetchProductWithMinPrice(minPrice);
		return priceProd;		
	}
	
	public List<Product> fetchProductForMaxPrice(int maxPrice){
		List<Product> priceProd = compareRepo.fetchProductWithMaxPrice(maxPrice);
		return priceProd;		
	}
	
	public List<Product> fetchProductForPriceRange(int minPrice, int maxPrice) {
		List<Product> priceProd = compareRepo.fetchProductInPriceRange(minPrice, maxPrice);
		return priceProd;
	}

}
