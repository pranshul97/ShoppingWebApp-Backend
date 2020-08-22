package com.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.project.entity.Product;
import com.project.entity.Retailers;
import com.project.entity.User;
import com.project.repository.RetailerRepo;
import com.project.repository.RetailerRepoImpl;

@SpringBootTest
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRetailerTests {

	/*@Autowired
	RetailerRepoImpl retailerImpl;*/
	
	@Test
	void addRetailer() {
		
		//
		/*
		Retailers ret=new Retailers();
		ret.setName("Majnu Bhai");
		ret.setEmail("majnu@gmail");
		ret.setContactNumber(9999999999L);
		ret.setPassword("majnu@123");
		
		RetailerRepo retailerImpl=new RetailerRepoImpl();
		retailerImpl.save(ret);*/
		
		User user=new User();
		user.setName("Pranshul");
		user.setContactNumber(100000000);
		user.setEmail("pranshul@iab");
		user.setPassword("123456");
		
		RetailerRepo retailerImpl=new RetailerRepoImpl();
		retailerImpl.save(user);
	}
}
