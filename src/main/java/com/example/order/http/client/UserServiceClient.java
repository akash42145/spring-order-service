package com.example.order.http.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.order.model.Customer;



@FeignClient("spring-user-service")
public interface UserServiceClient {

	@GetMapping("/customer/{id}")
	Customer getCustomer(@PathVariable int id);
	
	
	@GetMapping("/customer/owner")
	public String getOwner();

}
