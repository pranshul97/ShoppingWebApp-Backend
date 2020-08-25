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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.Dto.PictureDto;
import com.project.Dto.ProductDto;
import com.project.controller.FetchProductController.Log.Statustype;
import com.project.controller.ProductByRetailerController.Status;
import com.project.entity.Category;
import com.project.entity.Image;
import com.project.entity.Product;
import com.project.entity.Retailers;
import com.project.exception.ProductsException;
import com.project.exception.RetailerServiceException;
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
