package com.project.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.Dto.CartDto;
import com.project.Dto.PictureDto;
import com.project.Dto.ProductDto;
import com.project.controller.FetchProductController.Log.Statustype;
import com.project.controller.ProductByRetailerController.Status;
import com.project.controller.RetailerController.Status.StatusType;
import com.project.entity.Cart;
import com.project.entity.Category;
import com.project.entity.Image;
import com.project.entity.Product;
import com.project.entity.Retailers;
import com.project.entity.User;
import com.project.exception.CompareServiceException;
import com.project.exception.ProductsException;
import com.project.exception.RetailerServiceException;
import com.project.service.CompareService;
import com.project.service.ProductService;
import com.project.service.RetailerService;

//----------------Created And Managed By Pranshul-------------------
@RestController
@CrossOrigin
public class FetchProductController {

	@Autowired
	private ProductService prodService;
	
	@Autowired
	private  RetailerService retailerService;
	
	@Autowired
	private CompareService compareService;
	
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
			List<ProductDto> prodList=new ArrayList<ProductDto>();
			for(Product li: list) {
				ProductDto prod=new ProductDto();
				prod.setProductId(li.getProductId());
				prod.setName(li.getName());
				prod.setBrandName(li.getBrandName());
				prod.setPrice(li.getPrice());
				prod.setCategoryId(li.getCategory().getCategoryId());
				prod.setCategoryName(li.getCategory().getCategoryName());
				prodList.add(prod);
			}
			log.setList(prodList);
			return log;
		}
		catch(ProductsException p) {
			Log log=new Log();
			log.setStatus(Statustype.FAILURE);
			log.setList(null);
			return log;
		}
	}
	
	@PostMapping("/fetchByCategory")
	public Log fetchProductsByCategory(@RequestBody String categoryName) {
		Log log=new Log();
		try {
			List<Product> prodList=compareService.fetchProductForCategoryFilter(categoryName);
			List<ProductDto> dtoProd=new ArrayList<ProductDto>();
			for(Product list: prodList) {
				ProductDto dto=new ProductDto();
				dto.setProductId(list.getProductId());
				dto.setBrandName(list.getBrandName());
				dto.setName(list.getName());
				dto.setPrice(list.getPrice());
				dto.setCategoryId(list.getCategory().getCategoryId());
				dto.setCategoryName(list.getCategory().getCategoryName());
				dtoProd.add(dto);
			}
			log.setStatus(Statustype.SUCCESS);
			log.setList(dtoProd);
			return log;
		}
		catch (CompareServiceException e) {
			log.setStatus(Statustype.FAILURE);
			log.setList(null);
			return log;
		}
	}
	
	@PostMapping("/fetchByBrandName")
	public Log fetchProductsByBrandName(@RequestBody String brandName) {
		Log log=new Log();
		try {
			List<Product> prodList=compareService.fetchProductForBrand(brandName);
			List<ProductDto> dtoProd=new ArrayList<ProductDto>();
			for(Product list: prodList) {
				ProductDto dto=new ProductDto();
				dto.setProductId(list.getProductId());
				dto.setBrandName(list.getBrandName());
				dto.setName(list.getName());
				dto.setPrice(list.getPrice());
				dto.setCategoryId(list.getCategory().getCategoryId());
				dto.setCategoryName(list.getCategory().getCategoryName());
				dtoProd.add(dto);
			}
			log.setStatus(Statustype.SUCCESS);
			log.setList(dtoProd);
			return log;
		}
		catch (CompareServiceException e) {
			log.setStatus(Statustype.FAILURE);
			log.setList(null);
			return log;
		}
	}
	
	
	
	@PostMapping("/product-pic-upload")
	public Status upload(PictureDto productPicDto) {
		String imageUploadLocation = "d:/uploads/";
		int len=productPicDto.getProductPic().length;
		String fileNames[]=new String[len];
		int i=0;
		for(MultipartFile pic: productPicDto.getProductPic()) {
			fileNames[i] = pic.getOriginalFilename();
			String targetFile = imageUploadLocation + fileNames[i++];
			try {
				FileCopyUtils.copy(pic.getInputStream(), new FileOutputStream(targetFile));
			} catch (IOException e) {
				e.printStackTrace();
				Status status = new Status();
				status.setStatus(com.project.controller.ProductByRetailerController.Status.StatusType.FAILURE);
				status.setMessage(e.getMessage());
				return status;
			}
		}
		
		try {
			
			Category category = retailerService.addCategory(productPicDto.getCategoryName());

			Retailers retailers = retailerService.isProductPresent(productPicDto.getName(),productPicDto.getRetailerId());

			Product product = new Product();
			product.setBrandName(productPicDto.getBrandName());
			product.setCategory(category);
			product.setDescription(productPicDto.getDescription());
			product.setModel(productPicDto.getModel());
			product.setName(productPicDto.getName());
			product.setPrice(productPicDto.getPrice());
			product.setQuantity(productPicDto.getQuantity());
			product.setRetailer(retailers);
			retailerService.addProductByRetailer(product);
			
			Product pro=retailerService.getProductBynameId(product.getName(), retailers.getRetailerId());
			for(i=0;i<len;i++) {
				Image img=new Image();
				img.setImageLink(fileNames[i]);
				img.setProduct(pro);
				retailerService.addProductWithImage(img);
			}
			
			
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
		/*Customer customer = customerService.get(profilePicDto.getCustomerId());
		customer.setProfilePic(fileName);
		customerService.update(customer);
		
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setMessage("Uploaded!");
		return status;*/
	}
	
	@GetMapping("/fetchBrandNames")
	public List<String> fetchBrandNames(){
		return prodService.fetchBrands();
	}
	
	
	@PostMapping("/insertCartItem")
	public Status insertCartItem(@RequestBody CartDto cartDto) {
		Status status=new Status();
		try {
			Product prod=prodService.fetchProduct(cartDto.getProductId());
			User usr=prodService.fetchUser(cartDto.getUserId());
			
			Cart cart=new Cart();
			cart.setProduct(prod);
			cart.setUser(usr);
			cart.setQuantity(1);
			
			prodService.addProductToCart(cart);
			
			status.setStatus(com.project.controller.ProductByRetailerController.Status.StatusType.SUCCESS);
			status.setMessage("Product Added Succesfully!");
			return status;
		}
		catch (ProductsException e) {
			// TODO: handle exception
			status.setStatus(com.project.controller.ProductByRetailerController.Status.StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}
	
	
	
	public static class Log{
		private Statustype status;
		private List<ProductDto> list;
		public static enum Statustype{
			SUCCESS,FAILURE;
		}
		public Statustype getStatus() {
			return status;
		}
		public void setStatus(Statustype status) {
			this.status = status;
		}
		public List<ProductDto> getList() {
			return list;
		}
		public void setList(List<ProductDto> list) {
			this.list = list;
		}
		
		
	}
}
