package com.app.microservices2.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Client implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	private BigDecimal id;
	private String name;
	private String phone;
	private String address;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
