package com.deloitte.bankcustomer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.bankcustomer.dto.AadharDetails;
import com.deloitte.bankcustomer.entity.CustomerDetails;
import com.deloitte.bankcustomer.service.CustomerDetails_Service;

@RestController 
@RequestMapping("/customerdetails")
public class CustomerDetailsController {
	
	@Autowired 
	CustomerDetails_Service service;
	
	@PostMapping 
	public ResponseEntity<String> addCustomerDetails(@RequestBody CustomerDetails details){
		
		int accno = service.addCustomerDetails(details);
		return new ResponseEntity<String>("customer with account number "+accno+" added successfully!!",HttpStatus.OK);
	}
	
	@GetMapping("/{customerno}")
	public ResponseEntity<CustomerDetails> getDetailsByNumber(@PathVariable("customerno") int customernumber){
		CustomerDetails details = service.getCustomerDetailsByAccountNumber(customernumber);
		return new ResponseEntity<CustomerDetails>(details,HttpStatus.OK);
	}
	
	@GetMapping("/allcustomerdetails")
	public ResponseEntity<List<CustomerDetails>> getAllCustomerDetails(){
		
		List<CustomerDetails> customerList = service.findAll();
		return new ResponseEntity<List<CustomerDetails>>(customerList,HttpStatus.OK);
	}
	
	@DeleteMapping("/{customerno}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("customerno") int customerno)
	{
		service.deleteCustomer(customerno);
		return new ResponseEntity<String>("customer with "+customerno+"deleted",HttpStatus.OK);
	}
	@GetMapping("/verify/{customerno}/{aadharnumber}")
	public ResponseEntity<String> verifyCustomerDetails(@PathVariable("customerno") int customernumber , @PathVariable("aadharnumber") String aadharnumber)
	{    
		//verifying customer  aadhardetails(KYC).
		service.verifyCustomerDetailsUsingAadhar(customernumber,aadharnumber);
		return new ResponseEntity<String>("verified",HttpStatus.OK);
	}
	
	@GetMapping("/verifypan/{customerno}/{pancardnumber}")
	public ResponseEntity<String> verifyCustomerPanDetails(@PathVariable("customerno") int customernumber , @PathVariable("pancardnumber") String pancardnumber)
	{
		service.verifyCustomerDetailsByPan(customernumber , pancardnumber);
		return new ResponseEntity<String>("verified pan",HttpStatus.OK);
	}
	
	
	
}
