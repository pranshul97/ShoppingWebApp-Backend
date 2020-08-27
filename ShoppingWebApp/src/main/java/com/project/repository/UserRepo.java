package com.project.repository;

import java.util.List;

import com.project.entity.Order;
import com.project.entity.User;

public interface UserRepo {

	void addUser(User user);

	User findById(int userId);

	boolean isUserPresent(String email);

	int findByEmailAndPassword(String email, String password);
	
	List<Order> displayOrderForUser(int userId);
	

	
	

}