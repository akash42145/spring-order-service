package com.example.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order.model.Customer;
import com.example.order.model.Order;
import com.example.order.model.Product;
import com.example.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;	
	
	@GetMapping("place-order/{userId}/{productId}")
	public Order placeOrder(@PathVariable int userId, @PathVariable int productId) {
		return orderService.placeOrder(userId, productId);
		
	}
	
	@GetMapping("/getUser/{userId}")
	public Customer getUser(@PathVariable int userId) {
		return orderService.getUser(userId);
		
	}
	
	@GetMapping("/getProduct/{productId}")
	public Product getProduct(@PathVariable int productId) {
		return orderService.getProduct(productId);
		
	}
	
	@GetMapping("/owner-customer-product-feign")
	public String getCustomerProductOwnerUsingFeign() {
		return orderService.getCustomerProductOwner();
		
	}
	
	@GetMapping("/owner-customer-product-rest")
	public String getCustomerProductOwnerUsingRest() {
		return orderService.getCustomerProductOwnerUsingRest();
		
	}
	
	@GetMapping("/owner-customer-product-webclient")
	public String getCustomerProductOwnerUsingwebclient() {
		return orderService.getCustomerProductOwnerUsingwebclient();
		
	}

}
