package com.shopping.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.shopping.entity.Retailers;

@Component
public class RetailerRepoImpl implements RetailerRepo {

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public void save(Retailers ret) {
		em.merge(ret);
	}
}
