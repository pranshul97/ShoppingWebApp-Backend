package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.controller.FetchProductController.Log.Statustype;
import com.project.entity.Product;
import com.project.exception.ProductsException;
import com.project.service.ProductService;

//----------------Created And Managed By Pranshul-------------------
@RestController
@CrossOrigin
public class FetchProductController {

	@Autowired
	private ProductService prodService;
	
	@GetMapping("/fetchProduct")
	public Product fetchProduct(@RequestParam("productId") int productId) {
		//System.out.println(prodService.fetchProduct(productId).getRetailer().getRetailerId());
		return prodService.fetchProduct(productId);
	}
	
	
	@PostMapping("/fetchByName")
	public Log fetchProductsByName(@RequestBody String productName) {
		try {
			List<Product> list=prodService.fetchByName(productName);
			Log log=new Log();
			log.setStatus(Statustype.SUCCESS);
			log.setList(list);
			return log;
		}
		catch(ProductsException p) {
			Log log=new Log();
			log.setStatus(Statustype.FAILURE);
			log.setList(null);
			return log;
		}
	}
	
	public static class Log{
		private Statustype status;
		private List<Product> list;
		public static enum Statustype{
			SUCCESS,FAILURE;
		}
		public Statustype getStatus() {
			return status;
		}
		public void setStatus(Statustype status) {
			this.status = status;
		}
		public List<Product> getList() {
			return list;
		}
		public void setList(List<Product> list) {
			this.list = list;
		}
		
		
	}
}
