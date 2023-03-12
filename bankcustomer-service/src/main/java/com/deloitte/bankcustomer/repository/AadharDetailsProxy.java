package com.deloitte.bankcustomer.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.deloitte.bankcustomer.dto.AadharDetails;

@FeignClient("aadhardetails-service")
public interface AadharDetailsProxy {
	
	@GetMapping("aadhar/aadhardetails/{aadharcardNumber}")
	AadharDetails findDetailsByNumber(@PathVariable("aadharcardNumber") String aadharnumber);

}
