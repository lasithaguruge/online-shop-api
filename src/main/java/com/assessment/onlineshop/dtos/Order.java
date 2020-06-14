package com.assessment.onlineshop.dtos;

import java.util.ArrayList;

public class Order {
    private String id;
    private ArrayList<OrderItem> items;
    private double subTotal;
    private double discount;
    private double total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
