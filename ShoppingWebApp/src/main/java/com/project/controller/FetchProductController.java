package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Product;
import com.project.service.ProductService;

//----------------Created And Managed By Pranshul-------------------
@RestController
@CrossOrigin
public class FetchProductController {

	@Autowired
	private ProductService prodService;
	
	@GetMapping("/fetchProduct")
	public Product fetchProduct(@RequestParam("productId") int productId) {
		return prodService.fetchProduct(productId);
	}
}
