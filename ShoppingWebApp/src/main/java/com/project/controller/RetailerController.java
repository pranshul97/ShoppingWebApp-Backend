//----------------RetailerController by bhavya------------------------------------------------------------------------------------------
package com.project.controller;

import java.util.List;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Dto.RetailerLoginDto;
import com.project.controller.RetailerController.Status.StatusType;
import com.project.entity.Category;
import com.project.entity.Product;
import com.project.entity.Retailers;
import com.project.exception.RetailerServiceException;
import com.project.repository.RetailerRepository;
import com.project.service.RetailerService;

@RestController
@CrossOrigin
public class RetailerController {
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private RetailerRepository retailerRepository;
	
	@Autowired
	private RetailerService retailerService;
	
	@PostMapping("/retailerRegister")
	public Status register(@RequestBody Retailers retailers) {
			Status status = new Status();
			try{retailerService.addRetailer(retailers);
			
				
			
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration successful");
			//return status;
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("choudharymayank145@gmail.com");
			message.setTo(retailers.getEmail());
			message.setSubject("Thank You for registration");
			message.setText("Thank you "+retailers.getName()+" for registering for business with us on shopping.com. Keep Exploring for more products");
			mailSender.send(message);
			}catch(RetailerServiceException e){
				status.setStatus(StatusType.FAILURE);
				status.setMessage(e.getMessage());
				return status;
			}
			return status;
			
		
		
	}
	
	@PostMapping("/retailerLogin")
	public LoginStatus login(@RequestBody RetailerLoginDto retailerLoginDto) {
		try {
			retailerLoginDto.setPassword(RetailerService.getHashedString(retailerLoginDto.getPassword()));
			System.out.println(retailerLoginDto.getEmail());
			System.out.println(retailerLoginDto.getPassword());
			Retailers retailers = retailerService.login(retailerLoginDto.getEmail(), retailerLoginDto.getPassword());
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(StatusType.SUCCESS);
			loginStatus.setMessage("Login Successful");
			loginStatus.setRetailerId(retailers.getRetailerId());
			loginStatus.setName(retailers.getName());
			return loginStatus;
		}
		catch(RetailerServiceException e) {
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
		}
		
		
		
	}
	
	@GetMapping("/fetchCategory")
	public List<Category> fetchCategory(){
		return retailerService.fetchCategory();
	}
	
	@GetMapping("/fetchRetailer")
	public Status fetchRetailer(@RequestParam("retailerId") int retailerId) {
		//System.out.println(prodService.fetchProduct(productId).getRetailer().getRetailerId());
		Retailers retailers = new Retailers();
		
		retailers = retailerRepository.findById(retailerId);
		Status status = new Status();
		status.setStatus(StatusType.SUCCESS);
		status.setRetailers(retailers);
		return status;
		
	}
	
	public static class LoginStatus extends Status{
		RetailerLoginDto retailerLoginDto = new RetailerLoginDto();
		
		
	}
	
	public static class Status{
		private StatusType status;
		private String message;
		private int retailerId;
		private String name;
		private Retailers retailers;
		
		public Retailers getRetailers() {
			return retailers;
		}

		public void setRetailers(Retailers retailers) {
			this.retailers = retailers;
		}

		public int getRetailerId() {
			return retailerId;
		}

		public void setRetailerId(int retailerId) {
			this.retailerId = retailerId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
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
