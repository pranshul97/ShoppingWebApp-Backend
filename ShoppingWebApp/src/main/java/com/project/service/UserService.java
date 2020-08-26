package com.project.service;

import java.security.MessageDigest;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

import com.project.entity.User;

public interface UserService {

	void register(User user);

	User login(String email, String password);

	public static String getHashedString(String text) {
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
	}

}