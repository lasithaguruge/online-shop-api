package com.assessment.onlineshop.controllers;

import com.assessment.onlineshop.dtos.Order;
import com.assessment.onlineshop.services.OrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/orders/priceEngine")
    public Order calculateOrderTotal(@Validated @RequestBody Order order) {
        double orderTotal = orderService.calculateOrderTotal(order.getItems());
        Order newOrder = new Order();
        newOrder.setId(UUID.randomUUID().toString());
        newOrder.setItems(order.getItems());
        newOrder.setTotal(orderTotal);

        return newOrder;
    }
}
