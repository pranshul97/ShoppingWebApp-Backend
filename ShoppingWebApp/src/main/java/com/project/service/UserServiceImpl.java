//----------------------UserServiceImpl by Mayank------------------
package com.project.service;

import java.security.MessageDigest;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.project.entity.User;
import com.project.exception.UserServiceException;
import com.project.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo  ur;
	
	@Override
	public void register(User user) {
		
		if(!ur.isUserPresent(user.getEmail())) {
			ur.addUser(user);
		}
		else {
			throw new UserServiceException("User Already Registered");
		}	
	}
	
	@Override
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
	
	/*public static String getHashedString(String text) {
		try {
			text = Base64.getEncoder().encodeToString(text.getBytes());

			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(text.getBytes());

			byte[] digest = md.digest();
			text = DatatypeConverter.printHexBinary(digest).toUpperCase();

			return text;

		} catch (Exception e) {
			return Base64.getEncoder().encodeToString(text.getBytes());
		}
	}*/

}
