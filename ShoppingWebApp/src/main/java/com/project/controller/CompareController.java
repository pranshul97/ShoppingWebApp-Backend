//---------------------------------MADHAV controller class--------------------------------------------------------


package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Dto.CategoryDto;
import com.project.Dto.CompareDto;
import com.project.controller.CompareController.Log.Statustype;
import com.project.entity.Category;
import com.project.entity.Product;
import com.project.entity.Retailers;
import com.project.service.CompareService;

@RestController
@CrossOrigin
public class CompareController {
	
	@Autowired
	private CompareService compareService;
	
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
		List<Product> catProd = new ArrayList<Product>();
		List<Product> brandProd = new ArrayList<Product>();
		//List<Product> rangeProd = new ArrayList<Product>();
		//List<Product> maxPriceProd = new ArrayList<Product>();
		//List<Product> minPriceProd = new ArrayList<Product>();
		int sizeOfCatArray = categoryDto.catName.length;
		int sizeOfBrandArray = categoryDto.brandName.length;
		if(sizeOfCatArray>0) {
			List<Product> tempList = new ArrayList<Product>();
			for(int i=0; i<sizeOfCatArray; i++) {
				System.out.println("this is the category"+categoryDto.catName[i]);
				tempList = compareService.fetchProductForCategoryFilter(categoryDto.catName[i]);
				for(Product p : tempList)
					catProd.add(p);
			}
		}
		if(sizeOfBrandArray>0) {
			List<Product> tempList = new ArrayList<Product>();
			for(int i=0; i<sizeOfBrandArray; i++) {
				tempList = compareService.fetchProductForBrand(categoryDto.brandName[i]);
				//brandProd = compareService.fetchProductForBrand(categoryDto.brandName[i]); //This is giving products for only one brand (Maybe Overwrting the values of list)
				for(Product p : tempList)
					brandProd.add(p);
			}
		}
		
		/*if(categoryDto.minPrice !=0 && categoryDto.maxPrice!=0) {
			mainList = compareService.fetchProductForPriceRange(categoryDto.minPrice, categoryDto.maxPrice);
		}
		if(categoryDto.minPrice == 0 && categoryDto.maxPrice !=0) {
			mainList = compareService.fetchProductForMaxPrice(categoryDto.maxPrice);
		}
		if(categoryDto.minPrice != 0 && categoryDto.maxPrice ==0) {
			mainList = compareService.fetchProductForMinPrice(categoryDto.minPrice);
		}*/
		
		for(Product p : catProd)
			mainList.add(p);
		for(Product p : brandProd)
			mainList.add(p);
		/*for(Product p : rangeProd)
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
	
	
	@PostMapping("/cart")
	public Log fetchProductForCartForSomeUserIdr(@RequestBody int userId) {
		List<Product> cartList = compareService.fetchProductForCart(userId);
		
		Log log = new Log();
		if(cartList.size()>0) {
			log.setStatus(Statustype.SUCCESS);
			log.setList(cartList);
			return log;
		}
		else {
			log.setStatus(Statustype.FAILURE);
			log.setList(cartList);
			return log;
		}
			
		
	}
	
	@GetMapping("/admin")
	public Log fetchProductRetailerCategory() {
		List<Product> prodList = compareService.fetchAllProductforAdmin();
		List<Retailers> retList = compareService.fetchAllRetailersforAdmin();
		List<Category> catList = compareService.fetchAllCategoriesforAdmin();
		
		Log log=new Log();
		log.setStatus(Statustype.SUCCESS);
		log.setList(prodList);
		log.setrList(retList);
		log.setcList(catList);
		
		return log;
	}
	
	public static class Log{
		private Statustype status;
		private List<Product> list;
		private List<Retailers> rList;
		private List<Category> cList;
		
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
		public List<Retailers> getrList() {
			return rList;
		}
		public void setrList(List<Retailers> rList) {
			this.rList = rList;
		}
		public List<Category> getcList() {
			return cList;
		}
		public void setcList(List<Category> cList) {
			this.cList = cList;
		}
		

	}
}
