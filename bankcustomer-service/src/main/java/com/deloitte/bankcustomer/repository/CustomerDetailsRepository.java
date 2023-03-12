package com.deloitte.bankcustomer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.deloitte.bankcustomer.entity.CustomerDetails;

public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails , Integer> {

	@Query(value = "select max(accountNumber) from CustomerDetails")
	Optional<Integer> getMaxAccountNumber(); 
	
	//To get customer deatils by accountnumber the query is as follows
	
	@Query(value ="select customer from CustomerDetails customer where customer.accountNumber = ?1")
	CustomerDetails getCustomerDetailsByAccountNumber(int accountnumber);

	

	
}
