package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.service.UserService;

@Entity
@Table(name="tbl_user")
public class User {
	@Id
	@GeneratedValue
	@Column(name="User_Id")
	private int userId;
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Contact_Number")
	private long contactNumber;
	
	@Column(name="Password")
	private String password;
	
	public int getUserId() {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getPassword() {
		return password;
	}
	
	/*public void setPassword(String password) {
		this.password = UserService.getHashedString(password);

	}*/
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
