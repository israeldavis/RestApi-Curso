package com.vences.rest.dtos;

import java.util.List;

import com.vences.rest.entites.Order;

public class UserMmDto {
	
	private Long id;
	private String username;
	private String firstname;
	private String lastname;
	private List<Order> orders;
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

}
