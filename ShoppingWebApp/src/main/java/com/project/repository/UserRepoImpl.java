//------------------userRepository By Mayank---------
package com.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.entity.Order;
import com.project.entity.Product;
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
	
	@Override
	public List<Order> displayOrderForUser(int userId){
		String sql= "select o from Order o where o.user.userId =:usrId";
		  Query query = em.createQuery(sql);
		query.setParameter("usrId", userId);
		List<Order> orders = query.getResultList();
		
		return orders;
	}

	

	
}
