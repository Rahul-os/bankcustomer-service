package com.deloitte.bankcustomer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.deloitte.bankcustomer.repository.AadharDetailsProxy;
import com.deloitte.bankcustomer.repository.PanDetailsProxy;

@SpringBootApplication
@EnableDiscoveryClient 
@EnableFeignClients(clients = {AadharDetailsProxy.class , PanDetailsProxy.class}) //when i didnot add this I got the error as -> consider defining a bean of type com.deloitte.bankcustomer.repository.PanDetailsProxy
public class BankcustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankcustomerServiceApplication.class, args);
	}

}
