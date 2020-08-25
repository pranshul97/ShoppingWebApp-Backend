//---------------------------------MADHAV compare sevice class--------------------------------------------------------


package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Category;
import com.project.entity.Product;
import com.project.entity.Retailers;
import com.project.exception.CompareServiceException;
import com.project.repository.CompareRepo;

@Service
public class CompareMadhavServiceImpl implements CompareService{
	
	@Autowired
	private CompareRepo compareRepo;
	
	//Function to fetch product for comparison using pId
	@Override
	public Product fetchProductForCompare(int pId) {
		Product prod = compareRepo.fetchProductById(pId);
		return prod;
	}
	
	//Function to fetch product to display by filters of cateogy wise using category name 
	@Override
	public List<Product> fetchProductForCategoryFilter(String catName) {
		if(compareRepo.isCategoryPresent(catName)) {
			//System.out.println(catName);
			List<Product> catProduct  = new ArrayList<Product>();
			int catId = compareRepo.fetchCategoryIdByName(catName);
			catProduct = compareRepo.fetchproductByCategoryName(catId);
			return catProduct;
		}
		else
			throw new CompareServiceException("Category not found");	
	}
	
	//Function to fetch product to display by filters using brand name
	@Override
	public List<Product> fetchProductForBrand(String brandName) {
		if(compareRepo.isBrandPresent(brandName)) {
			List<Product> brandProduct  = new ArrayList<Product>();
			List<Integer> pIdList = compareRepo.fetchProductIdByBrandName(brandName);
			for(int i=0; i<pIdList.size(); i++) {
				brandProduct.add(compareRepo.fetchProductByBrandName(pIdList.get(i)));
			}
			return brandProduct;		
		}
		else
			throw new CompareServiceException("Brand not found");
	}
	
	
	//Function to fetch products for minimum price
	@Override
	public List<Product> fetchProductForMinPrice(double minPrice){
		List<Product> priceProd = compareRepo.fetchProductWithMinPrice(minPrice);
		return priceProd;		
	}
	
	//Function to fetch products for maximum price
	@Override
	public List<Product> fetchProductForMaxPrice(double maxPrice){
		List<Product> priceProd = compareRepo.fetchProductWithMaxPrice(maxPrice);
		return priceProd;		
	}
	
	//Function to fetch products for prince range
	@Override
	public List<Product> fetchProductForPriceRange(double minPrice, double maxPrice) {
		List<Product> priceProd = compareRepo.fetchProductInPriceRange(minPrice, maxPrice);
		return priceProd;
	}
	
	//Function to fetch all products for admin
	@Override
	public List<Product> fetchAllProductforAdmin(){
		if(compareRepo.isProductPresntForAdmin()) {
			List<Product> adminProduct = compareRepo.findAllProducts();
			return adminProduct;
		}
		else
			throw new CompareServiceException("There is no Prouct");
	}
	
	//Function to fetch all retailers for admin
	@Override
	public List<Retailers> fetchAllRetailersforAdmin(){
		if(compareRepo.isRetailerPresntForAdmin()) {
			List<Retailers> adminRetailers = compareRepo.findAllRetailers();
			return adminRetailers;
		}
		else
			throw new CompareServiceException("There is no retailers");
		
	}
		
	//Function to fetch all categories for admin
	@Override
	public List<Category> fetchAllCategoriesforAdmin(){
		if(compareRepo.isCategoryPresntForAdmin()) {
			List<Category> adminCategories = compareRepo.findAllCategory();
			return adminCategories;
		}
		else
			throw new CompareServiceException("There is no retailers");
		
	}

}
