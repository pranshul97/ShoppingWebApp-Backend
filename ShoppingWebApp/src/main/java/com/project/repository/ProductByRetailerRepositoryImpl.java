//---------managed by bhavya---------------------------------//
package com.project.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.entity.Category;
import com.project.entity.Product;
import com.project.entity.Retailers;

@Repository
public class ProductByRetailerRepositoryImpl implements ProductByRetailerRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void addProductByRetailer(Product product) {
		 entityManager.merge(product);
		
	}

	@Override
	public boolean findByProductId(int id) {
		return (Long)
				entityManager.
				createQuery("select count(p.productId) from Product p where p.productId = :id")
				.setParameter("id", id)
				.getSingleResult() == 1 ? true: false;
	}

	@Override
	public Retailers fetchRetailerById(int id) {
		 return entityManager.find(Retailers.class, id);
	}

	@Override
	public boolean isCategoryPresent(String name) {
		return (Long)entityManager.createQuery("Select count(c.categoryId) from Category c where UPPER(c.categoryName) LIKE UPPER(:pn)")
				.setParameter("pn", name)
				.getSingleResult()==1 ? true : false;
	}

	@Override
	public Category fetchCategory(String name) {
		return (Category)entityManager.createQuery("Select c from Category c where UPPER(c.categoryName) LIKE UPPER(:pn)")
				.setParameter("pn", name)
				.getSingleResult();
		
	}

	@Override
	@Transactional
	public void addCategory(Category cat) {
		 entityManager.merge(cat);
		
	}
	
	
	public boolean isProductPresent(int retailerId, String name) {
		return (Long)entityManager.createQuery("Select count(p.productId) from Product p where UPPER(p.name) LIKE UPPER(:pn) AND p.retailer.retailerId=:va")
				.setParameter("pn", name)
				.setParameter("va", retailerId )
				.getSingleResult()==1 ? true : false;
		
		
	}
	
	
	
	

}
