package com.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.project.entity.Product;
//-------- Created and managed by Pranshul-------------------------
@Repository
public class ProductRepoImpl implements ProductRepo {

	@PersistenceContext
	EntityManager em;
	
	
	public Product fetchById(int id) {
		return em.find(Product.class, id);
	}
	
	public boolean isProductPresent(String productName) {
		return (Long)em.createQuery("Select count(p.id) from Product p where UPPER(p.name) LIKE UPPER(:pn)")
				.setParameter("pn", productName)
				.getSingleResult()>=1 ? true : false;
	}
	
	
	public List<Product> fetchByName(String productName){
		return em.createQuery("Select p from Product p where UPPER(p.name) LIKE UPPER(:pn)")
				.setParameter("pn", productName)
				.getResultList();
	}
}
