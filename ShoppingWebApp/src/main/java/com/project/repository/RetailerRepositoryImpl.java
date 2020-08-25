 //----------------Retailer Repository Implementation by bhavya--------------------------------------------

package com.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.entity.Category;
import com.project.entity.Product;
import com.project.entity.Retailers;

@Repository
public class RetailerRepositoryImpl implements RetailerRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	@Transactional
	public void addRetailer(Retailers retailers) {
		entityManager.merge(retailers);
		
	}


	@Override
	public boolean isRetailerPresent(String email) {
		return (Long)
				entityManager
				.createQuery("select count(r.retailerId) from Retailers r where r.email = :em")
				.setParameter("em", email)
				.getSingleResult() == 1 ? true : false;
	
	
	}


	@Override
	public List<Retailers> findAllRetailers() {
		return entityManager
				.createNamedQuery("fetch-all")
				.getResultList();
	}

	@Override
	public int findByEmailAndPassword(String email, String password) {
		return (int) entityManager
				.createQuery("select r.retailerId from Retailers r where r.email = :em and r.password = :pw")
				.setParameter("em", email)
				.setParameter("pw", password)
				.getSingleResult();
	}


	@Override
	public Retailers findById(int retailerId) {
		return entityManager.find(Retailers.class, retailerId);
	}


	@Override
	public Product addProductByRetailer(Product product) {
		return entityManager.merge(product);
	}


	@Override
	public List<Category> fetchCategory() {
		return entityManager
				.createQuery("select c.categoryName from Category c")
				.getResultList();
	}

	
	
	
	
	

}
