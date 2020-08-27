package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Cart;
import com.project.entity.Image;
import com.project.entity.Product;
import com.project.entity.User;
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

	@Override
	public List<String> fetchBrands() {
		return prod.fetchBrandsRep();
		
	}
	
	
	
	public User fetchUser(int id) {
		return prod.fetchByUserId(id);
	}
	
	
	public void addProductToCart(Cart cart) {
		if(!prod.isProductPresentInCart(cart.getUser().getUserId(), cart.getProduct().getProductId())) {
			prod.saveProductToCart(cart);
		}
		else {
			throw new ProductsException("Product already exist in cart");
		}
	}
	
	public List<Image> getImagesOfProduct(int productId){
		if(prod.isImagePresent(productId)) {
			return prod.getImages(productId);
		}
		else {
			throw new ProductsException("No image Present of this product");
		}
	}
}
