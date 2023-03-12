package com.deloitte.bankcustomer.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deloitte.bankcustomer.dto.PancardDetails;
import com.deloitte.bankcustomer.repository.PanDetailsProxy;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class PanDetailsServiceR4J { 
	@Autowired 
	PanDetailsProxy pancardproxy;
	
	//applying circuit breaker
	@CircuitBreaker(name="pancarddetails-service" , fallbackMethod = "pancardDetailsServiceFallBack")
	public Object getDetailsByPanNumber(String pannumber)
	{
		PancardDetails details = pancardproxy.getDetailsByPancardNumber(pannumber);
		return details;
	}
	public Object pancardDetailsServiceFallBack(String pannumber , Exception e)
	{
		System.out.println("Pandetails service is down!!!... fallback route enable.... "+e.getMessage());
		System.out.println("CIRCUIT BREAKER ENABLED!!! No response form Pancardservice at this point of time."
				+ "Service will be back shortly...."+new Date());
		PancardDetails pandetails = new PancardDetails();
		return pandetails;
	}
}
