package com.deloitte.bankcustomer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler { 
	@ExceptionHandler(value = CustomerNotFoundException.class)
	
	public ResponseEntity<Object> exception(CustomerNotFoundException ex)
	{
	return new ResponseEntity<Object>("Customer is not present",HttpStatus.OK);
	}
	
	
}
