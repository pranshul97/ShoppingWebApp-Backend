//----------------------------------managed by bhavya--------------------------------------------------------------------------------------//
package com.project.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Dto.ProductAddDto;
import com.project.controller.RetailerController.Status;
import com.project.controller.RetailerController.Status.StatusType;
import com.project.entity.Category;
import com.project.entity.Product;
import com.project.entity.Retailers;
import com.project.exception.RetailerServiceException;
//import com.project.service.ProductByRetailerService;
import com.project.service.RetailerService;

@RestController
@CrossOrigin
public class ProductByRetailerController {
	
	@Autowired
	private  RetailerService retailerService;
	
	
	@PostMapping("/addProduct")
	public Status addProductByRetailer(@RequestBody ProductAddDto productDto) {
		
		try{
			Category category = retailerService.addCategory(productDto.getCategoryName());
			
			Retailers retailers = retailerService.isProductPresent(productDto.getName(), productDto.getRetailerId());
			
			Product product = new Product();
			product.setBrandName(productDto.getBrandName());
			product.setCategory(category);
			product.setDescription(productDto.getDescription());
			product.setModel(productDto.getModel());
			product.setName(productDto.getName());
			product.setPrice(productDto.getPrice());
			product.setQuantity(productDto.getQuantity());
			product.setRetailer(retailers);
			
			retailerService.addProductByRetailer(product);
		
			Status status = new Status();
			status.setStatus(com.project.controller.ProductByRetailerController.Status.StatusType.SUCCESS);
			status.setMessage("Product added successfully!");
			return status;
		}catch(RetailerServiceException e){
			Status status = new Status();
			status.setStatus(com.project.controller.ProductByRetailerController.Status.StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
		
	
	}
	
	public static class Status{
		private StatusType status;
		private String message;
		
		public static enum StatusType{
			SUCCESS,FAILURE;
		}

		public StatusType getStatus() {
			return status;
		}

		public void setStatus(StatusType status) {
			this.status = status;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
	}
	


}
