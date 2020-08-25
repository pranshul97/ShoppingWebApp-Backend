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
				.createQuery("select count(c.categoryId) from Category c where lower(c.categoryName) = lower(:cnm)")
				.setParameter("cnm", catName)
				.getSingleResult() >= 1 ? true : false;
	}
	
	@Override
	public int fetchCategoryIdByName(String catName) {
		return (Integer) entityManager
				.createQuery("select c.categoryId from Category c where lower(c.categoryName) = lower(:cnm)")
				.setParameter("cnm", catName)
				.getSingleResult();
	}
	
	@Override
	public List<Product> fetchproductByCategoryName(int catId) {
		return (List<Product>) entityManager
				.createQuery("select p from Product p where p.category.categoryId = :catId")
				.setParameter("catId", catId)
				.getResultList();
	}
	
	@Override
	public boolean isBrandPresent(String brandName) {
		return (Long)
				entityManager
				.createQuery("select count(p.productId) from Product p where lower(p.brandName) = lower(:bnm)")
				.setParameter("bnm", brandName)
				.getSingleResult() >= 1 ? true : false;
	}
	
	@Override
	public List<Integer> fetchProductIdByBrandName(String brandName) {
		return (List<Integer>) entityManager
				.createQuery("select p.productId from Product p where lower(p.brandName)= lower(:bnm)")
				.setParameter("bnm", brandName)
				.getResultList();
	}
	
	@Override
	public Product fetchProductByBrandName(int pId) {
		return (Product) entityManager
				.createQuery("select p from Product p where p.productId = :pid")
				.setParameter("pid", pId)
				.getSingleResult();
	}
	
	
	@Override
	public List<Product> fetchProductWithMinPrice(double minPrice) {
		return (List<Product>) entityManager
				.createQuery("select p from Product p where p.price >= :price" )
				.setParameter("price", minPrice)
				.getResultList();
	}
	
	@Override
	public List<Product> fetchProductWithMaxPrice(double maxPrice) {
		return (List<Product>) entityManager
				.createQuery("select p from Product p where p.price <= :price" )
				.setParameter("price", maxPrice)
				.getResultList();
	}
	
	@Override
	public List<Product> fetchProductInPriceRange(double minPrice, double maxPrice) {
		return (List<Product>) entityManager
				.createQuery("select p from Product p where p.price between :minP and :maxP")
				.setParameter("minP", minPrice)
				.setParameter("maxP", maxPrice)
				.getResultList();
	}
	
}
