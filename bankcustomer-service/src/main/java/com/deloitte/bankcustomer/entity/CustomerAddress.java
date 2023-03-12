package com.deloitte.bankcustomer.entity;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class CustomerAddress {  
	
	private String city;
	private String state;
	private String street;
	private String doorNo;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	
	
	
	
	

}
