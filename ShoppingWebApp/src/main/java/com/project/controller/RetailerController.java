//----------------RetailerController by bhavya------------------------------------------------------------------------------------------
package com.project.controller;

import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
			
			retailerService.addRetailer(retailers);
			
				
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration successful");
			return status;
			
		
		
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
