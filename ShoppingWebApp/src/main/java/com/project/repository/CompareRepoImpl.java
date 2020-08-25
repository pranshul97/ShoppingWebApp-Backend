//----------------------------COMPARE REPO BY MADHAV---------------------------------------

package com.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.project.entity.Product;

@Repository
public class CompareRepoImpl implements CompareRepo {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Product fetchProductById(int id){
		return entityManager.find(Product.class, id);
	}
	
	@Override
	public boolean isCategoryPresent(String catName) {
		return (Long)
				entityManager
				.createQuery("select count(c.Category_Id) from Category c where lower(c.name) = :cnm")
				.setParameter("cnm", catName)
				.getSingleResult() >= 1 ? true : false;
	}
	
	@Override
	public int fetchCategoryIdByName(String catName) {
		return (Integer) entityManager
				.createQuery("select c.Category_Id from Category c where lower(c.name) = :cnm")
				.setParameter("cnm", catName)
				.getSingleResult();
	}
	
	@Override
	public Product fetchproductByCategoryName(int catId) {
		return (Product) entityManager
				.createQuery("select p from Product p where p.Category_Id = :catId")
				.setParameter("catId", catId)
				.getResultList();
	}
	
	@Override
	public boolean isBrandPresent(String brandName) {
		return (Long)
				entityManager
				.createQuery("select count(p.Product_Id) from Product p where lower(p.Brand_name) = :bnm")
				.setParameter("bnm", brandName)
				.getSingleResult() >= 1 ? true : false;
	}
	
	@Override
	public List<Integer> fetchProductIdByBrandName(String brandName) {
		return (List<Integer>) entityManager
				.createQuery("select p.Product_Id from Product p where lower(p.Brand_Name) = :bnm")
				.setParameter("bnm", brandName)
				.getResultList();
	}
	
	@Override
	public Product fetchProductByBrandName(int pId) {
		return (Product) entityManager
				.createQuery("select p from Product p where p.Product_Id = :pid")
				.setParameter("pid", pId)
				.getResultList();
	}
	
	
	@Override
	public List<Product> fetchProductWithMinPrice(int minPrice) {
		return (List<Product>) entityManager
				.createQuery("select p from Product p where p.Price >= :price" )
				.setParameter("price", minPrice)
				.getResultList();
	}
	
	@Override
	public List<Product> fetchProductWithMaxPrice(int maxPrice) {
		return (List<Product>) entityManager
				.createQuery("select p from Product p where p.Price <= :price" )
				.setParameter("price", maxPrice)
				.getResultList();
	}
	
	@Override
	public List<Product> fetchProductInPriceRange(int minPrice, int maxPrice) {
		return (List<Product>) entityManager
				.createQuery("select p from Product p where p.Price >= :minP and <= :maxP")
				.setParameter("minP", minPrice)
				.setParameter("maxP", maxPrice)
				.getResultList();
	}
	
}
