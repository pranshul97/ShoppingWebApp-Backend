
	//-------------------------------UserController By Mayank------------------------------- 

package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.Dto.LoginDto;
import com.project.Dto.OrderDto;
import com.project.controller.UserController.Status.StatusType;
import com.project.entity.Order;
import com.project.entity.User;
import com.project.exception.UserServiceException;
import com.project.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/userregister")
	public Status register(@RequestBody User user) {
			
		Status status = new Status();
		try {
			 userService.register(user);
			
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Registration Successful!!");
			//return status;
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("choudharymayank145@gmail.com");
			message.setTo(user.getEmail());
			message.setSubject("Thank You for registration");
			message.setText("Thank you "+user.getName()+" for registering on shopping.com Keep Exploring for more products");
			mailSender.send(message);
			
			
		}
		catch(UserServiceException e) {
			//Status status = new Status();
			status.setStatus(StatusType.FAILURE);
			status.setMessage(e.getMessage());
			status.setMessage(e.getMessage());
			
		}
		
		return status;
	}
			
	
	
	
	@PostMapping("/login")
	public LoginStatus login(@RequestBody LoginDto loginDto) {
		
		try {
			loginDto.setPassword(UserService.getHashedString(loginDto.getPassword()));
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
	
	@GetMapping("/viewOrders")
	public List< OrderDto > viewAllOrders(@RequestParam("user_Id") int userId){
		
		List<Order> orders = userService.dsplayOrderForUser(userId);
		List<OrderDto> rs = new ArrayList<>();
		for(Order o: orders) {
			OrderDto od= new OrderDto();
			od.setId(o.getId());
			od.setOrderDate(o.getOrderDate());
			od.setQuantity(o.getQuantity());
			rs.add(od);
		}
		return rs;
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