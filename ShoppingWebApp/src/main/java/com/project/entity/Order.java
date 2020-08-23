 package com.project.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="tbl_order")
public class Order {

	@Id
	@GeneratedValue
	@Column(name="Order_Id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="User_Id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="Address_Id")
	private Address address;
	
	@Column(name="Payment_Id")
	private Payment payment;
	
	@Column(name="Status")
	private String status;
	
	@Column(name="Date_of_order")
	private LocalDate orderDate;
	
	@Column(name="Quantity_Ordered")
	private int quantity;
	
	@Column(name="Delivery_Date")
	private LocalDate deliveryDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	
}
