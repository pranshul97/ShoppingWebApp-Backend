//----------------------------COMPARE REPO BY MADHAV---------------------------------------

package com.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.project.entity.Category;
import com.project.entity.Product;
import com.project.entity.Retailers;

@Repository
public class CompareRepoImpl implements CompareRepo {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Product fetchProductById(int id){
		return entityManager.find(Product.class, id);
	}
	
	
	//Funcion to check whether category with given name is present or not
	@Override
	public boolean isCategoryPresent(String catName) {
		return (Long)
				entityManager
				.createQuery("select count(c.categoryId) from Category c where lower(c.categoryName) = lower(:cnm)")
				.setParameter("cnm", catName)
				.getSingleResult() >= 1 ? true : false;
	}
	
	//Function to get the category Id for given category name
	@Override
	public int fetchCategoryIdByName(String catName) {
		return (Integer) entityManager
				.createQuery("select c.categoryId from Category c where lower(c.categoryName) = lower(:cnm)")
				.setParameter("cnm", catName)
				.getSingleResult();
	}
	
	//Function to get all the products for any category id which was fetched for particular category name
	@Override
	public List<Product> fetchproductByCategoryName(int catId) {
		return (List<Product>) entityManager
				.createQuery("select p from Product p where p.category.categoryId = :catId")
				.setParameter("catId", catId)
				.getResultList();
	}
	
	//Funcion to check whether brand with given name is present or not
	@Override
	public boolean isBrandPresent(String brandName) {
		return (Long)
				entityManager
				.createQuery("select count(p.productId) from Product p where lower(p.brandName) = lower(:bnm)")
				.setParameter("bnm", brandName)
				.getSingleResult() >= 1 ? true : false;
	}
	
	//Function to get the category Id for given brand name
	@Override
	public List<Integer> fetchProductIdByBrandName(String brandName) {
		return (List<Integer>) entityManager
				.createQuery("select p.productId from Product p where lower(p.brandName)= lower(:bnm)")
				.setParameter("bnm", brandName)
				.getResultList();
	}
	
	//Function to get all the products for any category id which was fetched for particular category name
	@Override
	public Product fetchProductByBrandName(int pId) {
		return (Product) entityManager
				.createQuery("select p from Product p where p.productId = :pid")
				.setParameter("pid", pId)
				.getSingleResult();
	}
	
	
	//Function to fetch all the product where price name is >= given minimum price
	@Override
	public List<Product> fetchProductWithMinPrice(double minPrice) {
		return (List<Product>) entityManager
				.createQuery("select p from Product p where p.price >= :price" )
				.setParameter("price", minPrice)
				.getResultList();
	}
	
	//Function to fetch all the product where price name is <= given maximum price
	@Override
	public List<Product> fetchProductWithMaxPrice(double maxPrice) {
		return (List<Product>) entityManager
				.createQuery("select p from Product p where p.price <= :price" )
				.setParameter("price", maxPrice)
				.getResultList();
	}
	
	//Function to fetch all the product where price name is >= minimum price and <= maximum price
	@Override
	public List<Product> fetchProductInPriceRange(double minPrice, double maxPrice) {
		return (List<Product>) entityManager
				.createQuery("select p from Product p where p.price between :minP and :maxP")
				.setParameter("minP", minPrice)
				.setParameter("maxP", maxPrice)
				.getResultList();
	}
	

	@Override
	//Function to check whether there is any product in the database;
	public boolean isProductPresntForAdmin() {
		return (Long) entityManager
				.createQuery("select count(p.productId) from Product p")
				.getSingleResult() >= 1 ? true : false;
	}
	
	@Override
	//Function to fetch all the products present in the database
	public List<Product> findAllProducts(){
		return entityManager
				.createQuery("select p from Product p")
				.getResultList();
	}
	
	@Override
	//Function to check whether there is any retailer in the database;
	public boolean isRetailerPresntForAdmin() {
		return (Long) entityManager
				.createQuery("select count(r.retailerId) from Retailers r")
				.getSingleResult() >= 1 ? true : false;
	}
	
	@Override
	//Function to fetch all the Retailers present in the database
	public List<Retailers> findAllRetailers(){
		return entityManager
				.createQuery("select r from Retailers r")
				.getResultList();
	}
	
	@Override
	//Function to check whether there is any category in the database;
	public boolean isCategoryPresntForAdmin() {
		return (Long) entityManager
				.createQuery("select count(cat.categoryId) from Category cat")
				.getSingleResult() >= 1 ? true : false;
	}
	@Override
	//Function to fetch all the Categories present in the database
	public List<Category> findAllCategory(){
		return entityManager
				.createQuery("select cat from Category cat")
				.getResultList();
	}
	
}







