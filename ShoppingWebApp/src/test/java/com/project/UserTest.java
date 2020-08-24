//-----------------UserTesing By Mayank----
package com.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.project.entity.User;
import com.project.repository.UserRepo;

@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Rollback(false)
public class UserTest {
	
	@Autowired
	UserRepo ur;
	
	@Test
	void addUser() {
		User user=new User();
		user.setName("Mayank");
		user.setEmail("Mayank@lnt.com");
		user.setContactNumber(9999999999L);
		user.setPassword("mayank@123");
		
		ur.addUser(user);
		
	}
	
	@Test
	void fetchUser() {
		
		User user=ur.findById(9);
		//System.out.println(u.getUserId()+", "+u.getName()+", "+u.getContactNumber()+", "+u.getEmail());
	}
	
	@Test
	void fetchByEmailAndPassword() {
		
		int Id=ur.findByEmailAndPassword("Mayank@lnt.com","mayank@123");
		System.out.println(Id);
		
		//asserts missing
	}


}
