package com.example.order.http.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.order.model.Customer;

@FeignClient(name = "spring-user-service", fallback = UserClientFallback.class)
public interface UserServiceClient {

	@GetMapping("/customer/{id}")
	Customer getCustomer(@PathVariable int id);

	@GetMapping("/customer/owner")
	public String getOwner();

	@GetMapping("/customer/update-credits/{id}?credits={credits}")
	public Customer updateCustomerCredits(@PathVariable int id, @RequestParam double credits);

}
