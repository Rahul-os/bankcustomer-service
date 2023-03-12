package com.deloitte.bankcustomer.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.deloitte.bankcustomer.dto.PancardDetails;

@FeignClient("pancarddetails-service")
public interface PanDetailsProxy { 
	
	@GetMapping("pan/pancarddetails/{pancardNumber}")
	PancardDetails getDetailsByPancardNumber(@PathVariable("pancardNumber") String pannumber);

}
