//---------managed by bhavya---------------------------------//
package com.project.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.entity.Product;

@Repository
public class ProductByRetailerRepositoryImpl implements ProductByRetailerRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void addProductByRetailer(Product product) {
		entityManager.merge(product);
		
	}
	
	

}
