//----------------RetailerController by bhavya------------------------------------------------------------------------------------------
package com.project.controller;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Dto.RetailerLoginDto;
import com.project.controller.RetailerController.Status.StatusType;
import com.project.entity.Retailers;
import com.project.exception.RetailerServiceException;
import com.project.service.RetailerService;

@RestController
@CrossOrigin
public class RetailerController {
	
	@Autowired
	private RetailerService retailerService;
	
	@PostMapping("/retailerRegister")
	public Status register(@RequestBody Retailers retailers) {
			
			try{retailerService.addRetailer(retailers);
			
				
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration successful");
			return status;
			}catch(RetailerServiceException e){
				Status status = new Status();
				status.setStatus(StatusType.FAILURE);
				status.setMessage(e.getMessage());
				return status;
			}
			
		
		
	}
	
	@PostMapping("/retailerLogin")
	public LoginStatus login(@RequestBody RetailerLoginDto retailerLoginDto) {
		try {
			Retailers retailers = retailerService.login(retailerLoginDto.getEmail(), retailerLoginDto.getPasssword());
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
	
	public static class LoginStatus extends Status{
		RetailerLoginDto retailerLoginDto = new RetailerLoginDto();
		
		
	}
	
	public static class Status{
		private StatusType status;
		private String message;
		private int retailerId;
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

		private String name;
		
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
