package com.example.order.http.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.order.model.Product;


@FeignClient("spring-product-service")
public interface ProductServiceClient {
	
	@GetMapping("/product/{id}")
	Product getProduct(@PathVariable int id);
	
	@GetMapping("/product/change-inventory/{id}")
	public String changeInventory(@PathVariable int id, @RequestParam int count);
	
	@GetMapping("/product/owner")
	public String getOwner();
}
