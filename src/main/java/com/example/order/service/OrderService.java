package com.example.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.order.http.client.ProductServiceClient;
import com.example.order.http.client.UserServiceClient;
import com.example.order.model.Customer;
import com.example.order.model.Order;
import com.example.order.model.Product;
import com.example.order.model.Status;
import com.example.order.repository.OrderRepository;

import reactor.core.publisher.Mono;

@Service
public class OrderService {

	@Autowired
	private UserServiceClient userClient;

	@Autowired
	private ProductServiceClient productClient;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private RestTemplate restTemplate;

	@Transactional
	public Order placeOrder(int userId, int productId) {
		
		Product product = this.getProduct(productId);

		productClient.changeInventory(productId, -1);
		
		userClient.updateCustomerCredits(userId, product.getPrice());

		return orderRepository.save(new Order(userId, productId, Status.CREATED));
	}

	public String getCustomerProductOwner() {

		String customerOwner = userClient.getOwner();

		String productOwner = productClient.getOwner();

		return "Customer MS :" + customerOwner + ", Product MS :" + productOwner;
	}

	public Customer getUser(int userId) {

		return userClient.getCustomer(userId);
	}

	public Product getProduct(int productId) {

		return productClient.getProduct(productId);
	}

	public String getCustomerProductOwnerUsingRest() {
		
		  String customerOwner = restTemplate.exchange("http://spring-user-service/customer/owner",
	                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();
		  
		  String productOwner = restTemplate.exchange("http://spring-product-service/product/owner",
	                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();
		
		
		return "Customer MS :" + customerOwner + ", Product MS :" + productOwner;
	}

	public String getCustomerProductOwnerUsingwebclient() {
		
		WebClient client = webClientBuilder.baseUrl("http://spring-user-service").build();
		
		Mono<String> customerOwner = client
	      .get()
	      .uri("/customer/owner")
	      .retrieve()
	      .bodyToMono(String.class);
		
		WebClient client1 = webClientBuilder.baseUrl("http://spring-product-service").build();
		
		Mono<String> productOwner = client1
			      .get()
			      .uri("/product/owner")
			      .retrieve()
			      .bodyToMono(String.class);
		
		return "Customer MS :" + customerOwner.block() + ", Product MS :" + productOwner.block();
	}

}
