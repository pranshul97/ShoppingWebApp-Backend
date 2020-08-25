//---------------------------------MADHAV controller class--------------------------------------------------------


package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Dto.CategoryDto;
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
	
	@PostMapping("/category")
	public Log fetchProductWithCategoryFilter(@RequestBody CategoryDto categoryDto) {
		List<Product> mainList = new ArrayList<Product>();
		//List<Product> catProd = new ArrayList<Product>();
		//List<Product> brandProd = new ArrayList<Product>();
		//List<Product> rangeProd = new ArrayList<Product>();
		//List<Product> maxPriceProd = new ArrayList<Product>();
		//List<Product> minPriceProd = new ArrayList<Product>();
		int sizeOfCatArray = categoryDto.catName.length;
		int sizeOfBrandArray = categoryDto.brandName.length;
		if(sizeOfCatArray>0) {
			for(int i=0; i<sizeOfCatArray; i++) {
				System.out.println("this is the category"+categoryDto.catName[i]);
				mainList = compareService.fetchProductForCategoryFilter(categoryDto.catName[i]);
			}
		}
		if(sizeOfBrandArray>0) {
			for(int i=0; i<sizeOfCatArray; i++) {
				mainList = compareService.fetchProductForBrand(categoryDto.brandName[i]);
			}
		}
		if(categoryDto.minPrice != 0 && categoryDto.maxPrice != 0) {
			mainList = compareService.fetchProductForPriceRange(categoryDto.minPrice, categoryDto.maxPrice);
		}
		if(categoryDto.minPrice == 0 && categoryDto.maxPrice != 0) {
			mainList = compareService.fetchProductForMaxPrice(categoryDto.maxPrice);
		}
		if(categoryDto.minPrice != 0 && categoryDto.maxPrice == 0) {
			mainList = compareService.fetchProductForMinPrice(categoryDto.minPrice);
		}
		
		/*for(Product p : catProd)
			mainList.add(p);
		for(Product p : brandProd)
			mainList.add(p);
		for(Product p : rangeProd)
			mainList.add(p);
		for(Product p : maxPriceProd)
			mainList.add(p);
		for(Product p : minPriceProd)
			mainList.add(p);*/
		
		Log log=new Log();
		log.setStatus(Statustype.SUCCESS);
		log.setList(mainList);
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
