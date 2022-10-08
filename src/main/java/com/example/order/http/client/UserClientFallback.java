package com.example.order.http.client;

import org.springframework.stereotype.Component;

import com.example.order.model.Customer;

@Component
public class UserClientFallback implements UserServiceClient {
   

	@Override
	public Customer getCustomer(int id) {
		
		return new Customer();
	}

	@Override
	public String getOwner() {
		
		return "user Service is down, Falling back to legacy service!!";
	}

	@Override
	public Customer updateCustomerCredits(int id, double credits) {
		
		return new Customer();
	}
}