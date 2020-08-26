package com.project.service;

import com.project.entity.User;

public interface UserService {

	void register(User user);

	User login(String email, String password);

	/*static String getHashedString(String password) {
		// TODO Auto-generated method stub
		return null;
	}*/

}