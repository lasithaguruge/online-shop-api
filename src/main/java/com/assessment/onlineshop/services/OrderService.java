package com.assessment.onlineshop.services;

import com.assessment.onlineshop.dtos.OrderItem;

import java.util.ArrayList;

public interface OrderService {
    double calculateOrderTotal(ArrayList<OrderItem> orderItems);
}
