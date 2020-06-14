package com.assessment.onlineshop.services;

import com.assessment.onlineshop.dtos.Order;

import java.util.ArrayList;

public interface OrderService {
    double calculateOrderTotal(ArrayList<Order.OrderItem> orderItems);
}
