//---------------------------------MADHAV compare sevice class--------------------------------------------------------


package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Product;
import com.project.repository.CompareRepo;

@Service
public class CompareMadhavServiceImpl {
	
	@Autowired
	private CompareRepo compareRepo;
	
	public Product fetchProductForCompare(int pId) {
		Product prod = compareRepo.fetchProductById(pId);
		return prod;
	}

}
