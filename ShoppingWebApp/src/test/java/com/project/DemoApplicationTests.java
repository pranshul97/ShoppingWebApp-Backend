package com.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.project.entity.Retailers;
import com.project.entity.User;
import com.project.repository.RetailerRepo;
import com.project.repository.RetailerRepoImpl;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class DemoApplicationTests {

	@Autowired
	private RetailerRepo ret;
	
	@Test
	void contextLoads() {
	}

	@Test
	void user() {
		
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
		user.setName("Amit");
		user.setContactNumber(745656521);
		user.setEmail("adfds@qwe");
		user.setPassword("548");
		
		//RetailerRepo retailerImpl=new RetailerRepoImpl();
		ret.save(user);
	}
	
	@Test
	void fetchUser() {
		User us=ret.fetch(User.class, 13);
		
		System.out.println(us.getUserId()+", "+us.getName()+", "+us.getContactNumber()+", "+us.getEmail());
	}
	
	
	@Test
	void saveRetailer() {
		Retailers retailer=new Retailers();
		retailer.setName("Majnu Bhai");
		retailer.setEmail("majnu@gmail");
		retailer.setContactNumber(9999999999L);
		retailer.setPassword("majnu@123");
		
		ret.save(retailer);
	}
	

}
