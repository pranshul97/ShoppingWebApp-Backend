//---------------------------------MADHAV controller class--------------------------------------------------------


package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Dto.CompareDto;
import com.project.controller.CompareController.Log.Statustype;
import com.project.entity.Product;
import com.project.service.CompareMadhavServiceImpl;

@RestController
@CrossOrigin
public class CompareController {
	
	@Autowired
	private CompareMadhavServiceImpl compareService;
	
	@PostMapping("/compare")
	//public List<Product> fetchProductDetails(@RequestBody CompareDto compareDto) {
	public Log fetchProductDetails(@RequestBody int[] arr) {
		List<Product> proList = new ArrayList<>();
		Log log=new Log();
		log.setStatus(Statustype.SUCCESS);
		
		//int idArrayLength = compareDto.arrayListSize();
		for(int i=0; i<arr.length ;i++) {
			//Product proDetails = compareService.fetchProductForCompare(compareDto.fetchElementValue(i));
			Product proDetails = compareService.fetchProductForCompare(arr[i]);
			proList.add(proDetails);
		}
		//System.out.println(proDetails.getName() + " " + proDetails.getCategory() + " " + proDetails.getModel());
		log.setList(proList);
		return log;
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
