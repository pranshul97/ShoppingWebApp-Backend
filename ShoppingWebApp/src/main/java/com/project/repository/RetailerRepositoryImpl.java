//----------------Retailer Repository Implementation by bhavya--------------------------------------------

package com.project.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	
	
	
	

}
