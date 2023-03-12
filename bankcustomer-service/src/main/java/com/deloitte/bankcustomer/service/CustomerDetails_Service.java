package com.deloitte.bankcustomer.service;

import java.util.List;

import com.deloitte.bankcustomer.entity.CustomerDetails;

public interface CustomerDetails_Service { 
	
	int addCustomerDetails(CustomerDetails details);
	
	CustomerDetails getCustomerDetailsByAccountNumber(int accountnumber);

	List<CustomerDetails> findAll();
	
	int deleteCustomer(int accountnumber);
	
	//to perform KYC 
	void verifyCustomerDetailsUsingAadhar(int accountnumber , String aadharnumber);
	
	//to verify pan details 
	void verifyCustomerDetailsByPan(int accountnumber , String pancardnumber);
	
	
}
