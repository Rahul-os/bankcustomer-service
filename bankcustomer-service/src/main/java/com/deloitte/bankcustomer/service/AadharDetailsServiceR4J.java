package com.deloitte.bankcustomer.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.bankcustomer.dto.AadharDetails;
import com.deloitte.bankcustomer.repository.AadharDetailsProxy;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
@Component
public class AadharDetailsServiceR4J { 
	
	@Autowired 
	AadharDetailsProxy aadharproxy; 
	
	
	
	//circuitbreakr 
	@CircuitBreaker(name = "aadhardetails-service" , fallbackMethod = "aadharDetailsServiceFallBack")
	public Object findDetailsByNumber(String aadharnumber)        //calling the method from the aadhardetails-service
	{
		AadharDetails details = aadharproxy.findDetailsByNumber(aadharnumber);
		return details;
	}
	
	public Object aadharDetailsServiceFallBack(String aadharnumber , Exception ex)
	{
		System.out.println("AadharDetails service is down!!!... fallback route enable.... "+ex.getMessage());
		System.out.println("CIRCUIT BREAKER ENABLED!!! No response form Aadharservice at this point of time."
				+ "Service will be back shortly...."+new Date());
		AadharDetails details = new AadharDetails();
		return details;
	}
}
