package com.project;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.project.entity.Product;
import com.project.repository.ProductRepo;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestingRepoByPranshul {

	@Autowired
	private ProductRepo pr;
	
	@Test
	void fetchProductByName() {
		List<Product> list=pr.fetchByName("Shoe");
	}
}
