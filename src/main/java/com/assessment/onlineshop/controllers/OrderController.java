package com.assessment.onlineshop.controllers;

import com.assessment.onlineshop.dtos.Order;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @PostMapping("/api/orders/priceEngine")
    public Order calculateOrderTotal(@Validated @RequestBody Order order) {

        return order;
    }
}
