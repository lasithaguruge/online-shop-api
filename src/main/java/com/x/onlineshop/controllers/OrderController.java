package com.x.onlineshop.controllers;

import com.x.onlineshop.models.Order;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @PostMapping("/api/orders")
    public Order addOrder(@Validated @RequestBody Order order) {
        return order;
    }
}
