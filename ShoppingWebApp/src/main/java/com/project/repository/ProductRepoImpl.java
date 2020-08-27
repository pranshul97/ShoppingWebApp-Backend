package com.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.entity.Cart;
import com.project.entity.Image;
import com.project.entity.Product;
import com.project.entity.User;
//-------- Created and managed by Pranshul-------------------------
@Repository
public class ProductRepoImpl implements ProductRepo {

	@PersistenceContext
	EntityManager em;
	
	
	public Product fetchById(int id) {
		return em.find(Product.class, id);
	}
	
	public boolean isProductPresent(String productName) {
		return (Long)em.createQuery("Select count(p.id) from Product p where UPPER(p.name) LIKE UPPER(:pn)")
				.setParameter("pn", "%"+productName+"%")
				.getSingleResult()>=1 ? true : false;
	}
	
	
	public List<Product> fetchByName(String productName){
		return em.createQuery("Select p from Product p where UPPER(p.name) LIKE UPPER(:pn)")
				.setParameter("pn", "%"+productName+"%")
				.getResultList();
	}

	@Override
	public List<String> fetchBrandsRep() {
		return em.createQuery("Select distinct(p.brandName) from Product p").getResultList();
	}
	
	
	public User fetchByUserId(int id) {
		return em.find(User.class, id);
	}
	
	
	public boolean isProductPresentInCart(int userId, int productId) {
		return (Long)em.createQuery("Select count(c.id) from Cart c where c.user.userId=:us AND c.product.productId=:pr")
				.setParameter("us", userId)
				.setParameter("pr", productId)
				.getSingleResult()>=1 ? true : false;
	}
	
	@Transactional
	public void saveProductToCart(Cart cart) {
		em.merge(cart);
	}
	
	
	public boolean isImagePresent(int productId) {
		return (Long)em.createQuery("Select count(i.imageId) from Image i where i.product.productId=:pi")
				.setParameter("pi", productId)
				.getSingleResult()>=1 ? true : false;
	}
	
	public List<Image> getImages(int productId){
		return em.createQuery("Select i from Image i where i.product.productId=:pi")
				.setParameter("pi", productId)
				.getResultList();
	}
	
	
}
