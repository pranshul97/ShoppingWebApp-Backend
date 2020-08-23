package com.project.repository;

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
}
