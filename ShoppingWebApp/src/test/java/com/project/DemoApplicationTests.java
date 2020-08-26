package com.project;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.project.entity.Address;
import com.project.entity.Cart;
import com.project.entity.Category;
import com.project.entity.Order;
import com.project.entity.Payment;
import com.project.entity.Product;
import com.project.entity.Retailers;
import com.project.entity.User;
import com.project.repository.ProductRepo;
import com.project.repository.RetailerRepo;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class DemoApplicationTests {

	@Autowired
	private RetailerRepo ret;
	
	@Autowired
	private ProductRepo prrepo;
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
		user.setName("Naman");
		user.setContactNumber(7485963232L);
		user.setEmail("prans@gmail.com");
		user.setPassword("5589");
		
		//RetailerRepo retailerImpl=new RetailerRepoImpl();
		ret.save(user);
	}
	
	@Test
	void fetchUser() {
		User us=ret.fetch(User.class, 1);
		
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
	
	@Test
	void saveCategory() {
		Category cat=new Category();
		cat.setCategoryName("Shoes");
		
		ret.save(cat);
	}
	
	@Test
	void saveProductForRetiler() {
		
		Retailers r=ret.fetch(Retailers.class, 18);
		
		Category cat=ret.fetch(Category.class, 19);
		
		Product prod=new Product();
		prod.setName("Shoe");
		prod.setPrice(1200.00);
		prod.setDescription("Shoe by Adidas");
		prod.setModel("M123");
		prod.setQuantity(12);
		prod.setCategory(cat);
		prod.setRetailer(r);
		prod.setBrandName("Adidas");
		ret.save(prod);
	}
	
	@Test
	void saveIteminCart() {
		
		Product prod=ret.fetch(Product.class, 23);
		
		User usr=ret.fetch(User.class, 9);
		
		Cart crt=new Cart();
		crt.setProduct(prod);
		crt.setUser(usr);
		crt.setQuantity(13);
		
		ret.save(crt);
	}
	
	@Test
	void addPayment() {
		Payment pay=new Payment();
		pay.setMode("COD");
		pay.setStatus("Completed");
		pay.setTimestamp(LocalDateTime.now());
		
		ret.save(pay);
	}
	
	@Test
	void addAddress() {
		User usr=ret.fetch(User.class, 13);
		
		Address add=new Address();
		add.setContactNumber(17895231211L);
		add.setHouseNumber("A-101");
		add.setLandmark("Aatank Gali");
		add.setLocality("Khunkhar Mohalla");
		add.setCity("Meerut");
		add.setState("UP");
		add.setCountry("India");
		add.setPincode(12356);
		add.setUser(usr);
		
		ret.save(add);
	}
	
	@Test
	void addOrder() {
		User usr=ret.fetch(User.class, 13);
		
		Address add=ret.fetch(Address.class, 27);
		
		Payment pay=ret.fetch(Payment.class, 26);
		
		Order ord=new Order();
		ord.setStatus("Delivered");
		ord.setOrderDate(LocalDate.now());
		ord.setQuantity(12);
		ord.setDeliveryDate(LocalDate.now());
		ord.setUser(usr);
		ord.setAddress(add);
		ord.setPayment(pay);
		
		ret.save(ord);
	}
	
	@Test
	void testproductByname() {
		
	}
	
	
	@Test
	void fetchBrandsTesting() {
		List<String> list=prrepo.fetchBrandsRep();
		for(String li : list) {
			System.out.println(li);
		}
	}
}








