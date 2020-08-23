package com.project.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.entity.User;

@Repository
public class UserRepoImpl implements UserRepo {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public void addUser(User user) {
		
				em.merge(user);
	}
	
	@Override
	public User findById(int userId) {
		return em.find(User.class,userId);
	}
	
	@Override
	public boolean isUserPresent(String email) {
		return (Long)
				em.createQuery("select count(u.userId) from User u where u.email= :em")
				.setParameter("em",email)
				.getSingleResult() == 1 ? true:false;
	}
	
	@Override
	public int findByEmailAndPassword(String email,String password) {
		return (Integer)
				em.createQuery("select u.userId from User u where u.email = :em and u.password = :pw")
				.setParameter("em",email)
				.setParameter("pw",password)
				.getSingleResult();
	}
	
}
