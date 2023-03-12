package com.deloitte.bankcustomer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.bankcustomer.dto.AadharDetails;
import com.deloitte.bankcustomer.dto.PancardDetails;
import com.deloitte.bankcustomer.entity.CustomerDetails;
import com.deloitte.bankcustomer.exception.CustomerNotFoundException;
import com.deloitte.bankcustomer.exception.ServerNotFoundException;
import com.deloitte.bankcustomer.repository.AadharDetailsProxy;
import com.deloitte.bankcustomer.repository.CustomerDetailsRepository;
import com.deloitte.bankcustomer.repository.PanDetailsProxy;

@Service
public class CustomerDetails_ServiceImpl implements CustomerDetails_Service {
	
	
	private int accountNumber = 10000;
	@Autowired 
	CustomerDetailsRepository repo;
	
	@Autowired
	AadharDetailsProxy proxy;

	@Autowired 
	PanDetailsProxy pancardproxy;
	
	@Autowired 
	AadharDetailsServiceR4J r4j; 
	
	@Autowired 
	PanDetailsServiceR4J panr4j;
	
	@Override 
	public int addCustomerDetails(CustomerDetails details) {
		
		Optional<Integer> id = repo.getMaxAccountNumber();
		if(id.isEmpty())
			details.setAccountNumber(accountNumber);
		else {
			accountNumber = id.get().intValue();
			accountNumber = accountNumber + 5;
			details.setAccountNumber(accountNumber);
		}
		
		details.setComments("KYC pending ,pan pending");
		
		repo.save(details);
		
		if(details.getAadharcardNumber() != null)
		{
			verifyCustomerDetailsUsingAadhar(details.getAccountNumber() , details.getAadharcardNumber());
		}
		else {
			
			details.setComments("aadhar not added");
			
		}
		
		
		
		//checking pan 
		if(details.getPancardNumber() != null )
		{
			verifyCustomerDetailsByPan(details.getAccountNumber() , details.getPancardNumber());
		}
		else {
			details.setComments(details.getComments()+"pan not added");
		}
		
		repo.save(details);
		return details.getAccountNumber();
	}

	@Override
	public CustomerDetails getCustomerDetailsByAccountNumber(int accountnumber) {
		
		return repo.getCustomerDetailsByAccountNumber(accountnumber);
		
		
	}

	@Override
	public List<CustomerDetails> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public int deleteCustomer(int accountnumber) {
		// TODO Auto-generated method stub 
		repo.deleteById(accountnumber);
		return 0;
	}
		
	
	

	@Override
	public void verifyCustomerDetailsUsingAadhar(int accountnumber ,String aadharnumber) {
		// kyc verification
		
		CustomerDetails customerdetails = repo.getCustomerDetailsByAccountNumber(accountnumber);
		if(customerdetails == null)
			throw new CustomerNotFoundException();
		
		//if(customerdetails.getAadharcardNumber() ==null)               //additionally added this line tonight
			customerdetails.setAadharcardNumber(aadharnumber); 
		
		//AadharDetails aadhardetails = proxy.findDetailsByNumber(aadharnumber);
		AadharDetails aadhardetails =(AadharDetails) r4j.findDetailsByNumber(aadharnumber);   //typecasting , calling this method that is present in AadharDetailsServiceR4J
		
		
		 
		if(aadhardetails != null) {
			//customerdetails.setComments(customerdetails.getComments()+"Aadhar verified successfully");  //kyc is done
			customerdetails.setComments("KYC done ");
		}
		
		else {
			
			customerdetails.setComments("KYC not done"); 
			repo.save(customerdetails);
			throw new ServerNotFoundException();         
		}
		repo.save(customerdetails);
		
		
	
		
}

	
	@Override
	public void verifyCustomerDetailsByPan(int accountnumber , String pancardnumber) {
		// TODO Auto-generated method stub
		
		//Panverification.
		
		CustomerDetails customerdetails = repo.getCustomerDetailsByAccountNumber(accountnumber);
		if(customerdetails == null )
			throw new CustomerNotFoundException();
		
		
			customerdetails.setPancardNumber(pancardnumber);
		
		//PancardDetails pandetails = pancardproxy.getDetailsByPancardNumber(customerdetails.getPancardNumber());
		PancardDetails pandetails = (PancardDetails) panr4j.getDetailsByPanNumber(pancardnumber); 
		
		if(pandetails!=null)
		{
			//customerdetails.setComments(customerdetails.getComments()+" pan verification success");
			customerdetails.setComments(customerdetails.getComments()+" pan verified");
		}
		else {
			//customerdetails.setComments(customerdetails.getComments()+" pan verification failed"); //concatinating these comments to the previous comments
			customerdetails.setComments(customerdetails.getComments()+"pan verification failed"); 
			repo.save(customerdetails);
			
			throw new ServerNotFoundException();
			
		}
		
		repo.save(customerdetails);
	}

	
		
	

	
	
	
	
	

}
