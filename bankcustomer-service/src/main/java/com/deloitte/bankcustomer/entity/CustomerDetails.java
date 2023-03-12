package com.deloitte.bankcustomer.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "bankcustomerdetails" )
public class CustomerDetails { 
	@Id
	private int accountNumber;
	@Column(length = 30)
	private String firstName;
	@Column(length = 20)
	private String lastName;
	@Column(length = 12)
	private long mobile;
	@Column(length = 30)
	private String email;
	@Column(length = 10)
	private String pancardNumber;
	@Column(length = 12)
	private String aadharcardNumber;
	@Column(length = 60)
	private String comments;
	@Embedded
	@Autowired 
	
	private CustomerAddress address;
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPancardNumber() {
		return pancardNumber;
	}
	public void setPancardNumber(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}
	public String getAadharcardNumber() {
		return aadharcardNumber;
	}
	public void setAadharcardNumber(String aadharcardNumber) {
		this.aadharcardNumber = aadharcardNumber;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public CustomerAddress getAddress() {
		return address;
	}
	public void setAddress(CustomerAddress address) {
		this.address = address;
	} 
	
	

}
