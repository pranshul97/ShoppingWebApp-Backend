package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Product;
import com.project.service.CompareMadhavServiceImpl;

@RestController
@CrossOrigin
public class CompareController {
	
	@Autowired
	private CompareMadhavServiceImpl compareService;
	
	@PostMapping("/compare")
	public Product fetchProductDetails(int pId) {
		Product proDetails = compareService.fetchProductForCompare(pId);
		System.out.println(proDetails.getName() + " " + proDetails.getCategory() + " " + proDetails.getModel());
		return proDetails;
	}

}
