//-------------------------------UserController By Mayank------------------------------- 

package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.project.Dto.LoginDto;
import com.project.controller.UserController.Status.StatusType;
import com.project.entity.User;
import com.project.exception.UserServiceException;
import com.project.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/userregister")
	public Status register(@RequestBody User user) {
		
		try {
			 userService.register(user);
			Status status = new Status();
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successful!!");
			return status;
		}
		catch(UserServiceException e) {
			Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			return status;
		}
	}
			
	
	
	
	@PostMapping("/login")
	public LoginStatus login(@RequestBody LoginDto loginDto) {
		
		try {
			User user = userService.login(loginDto.getEmail(), loginDto.getPassword());
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(StatusType.SUCCESS);
			loginStatus.setMessage("Login Successful!!");
			loginStatus.setUserId(user.getUserId());
			loginStatus.setName(user.getName());
			return loginStatus;
		}
		catch(UserServiceException e) {
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setStatus(StatusType.FAILURE);
			loginStatus.setMessage(e.getMessage());
			return loginStatus;
			
		}
			
	}
	
	public static class LoginStatus extends Status{
		
		private int userId;
		private String name;
		public int getuserId() {
			return userId;
		}
	
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	public static class Status{
        private StatusType status;
        private String message;
 
        public StatusType getStatus() {
            return status;
        }
        public String getMessage() {
            return message;
        }
        public void setStatus(StatusType status) {
            this.status = status;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public static enum StatusType{
            SUCCESS, FAILURE;
        }  
    }
		
}


	

	
	
	


