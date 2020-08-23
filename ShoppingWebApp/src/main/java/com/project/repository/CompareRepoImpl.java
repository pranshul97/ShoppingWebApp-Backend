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

}
