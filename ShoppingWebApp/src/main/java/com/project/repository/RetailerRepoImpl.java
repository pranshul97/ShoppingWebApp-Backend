package com.project.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.project.entity.Retailers;
import com.project.entity.User;

@Repository
public class RetailerRepoImpl implements RetailerRepo {

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Transactional
	public void save(User ret) {
		em.persist(ret);
	}
}
