package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.exception.UserServiceException;
import com.project.entity.User;
import com.project.exception.UserServiceException;
import com.project.repository.UserRepo;

@Service
public class UserServiceImpl implements UserServiceInterface {
	
	@Autowired
	private UserRepo  ur;
	
	public void register(User user) {
		
		if(!ur.isUserPresent(user.getEmail())) {
			ur.addUser(user);
		}
		else {
			throw new UserServiceException("User Already Registered");
		}	
	}
	
	public User login(String email,String password) {
		
		try {
			int userId =(int) ur.findByEmailAndPassword(email,password);
			User user=ur.findById(userId);
			return user;
		}
		catch(EmptyResultDataAccessException e){
			
			throw new UserServiceException("cannot login username or password is wrong");
			
		}
	}

}
