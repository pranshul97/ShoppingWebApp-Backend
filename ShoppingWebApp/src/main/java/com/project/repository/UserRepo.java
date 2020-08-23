package com.project.repository;

import com.project.entity.User;

public interface UserRepo {

	void addUser(User user);

	User findById(int userId);

	boolean isUserPresent(String email);

	int findByEmailAndPassword(String email, String password);

}