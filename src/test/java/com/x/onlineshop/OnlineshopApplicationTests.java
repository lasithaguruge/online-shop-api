package com.x.onlineshop;

import com.x.onlineshop.dtos.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = OnlineshopApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OnlineshopApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port + "/api";
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testGreeting() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/greeting",
				HttpMethod.GET, entity, String.class);

		Assertions.assertNotNull(response.getBody());
	}

	@Test
	public void testAddOrder() {
		Order order = new Order();
		order.setId("Prius");
		order.setTotal(4);

		ResponseEntity<Order> postResponse = restTemplate.postForEntity(getRootUrl() + "/orders", order, Order.class);
		Assertions.assertNotNull(postResponse);
		Assertions.assertNotNull(postResponse.getBody());
	}

}
