package com.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.shopping.entity.Product;
import com.shopping.entity.Retailers;
import com.shopping.repository.RetailerRepo;
import com.shopping.repository.RetailerRepoImpl;

@SpringBootTest
//@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRetailerTests {

	/*@Autowired
	RetailerRepoImpl retailerImpl;*/
	
	@Test
	public void addRetailer() {
		
		//
		
		Retailers ret=new Retailers();
		ret.setName("Majnu Bhai");
		ret.setEmail("majnu@gmail");
		ret.setContactNumber(9999999999L);
		ret.setPassword("majnu@123");
		
		RetailerRepo retailerImpl=new RetailerRepoImpl();
		retailerImpl.save(ret);
		
	}
}
