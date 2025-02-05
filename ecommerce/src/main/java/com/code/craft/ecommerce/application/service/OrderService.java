package com.code.craft.ecommerce.application.service;

import com.code.craft.ecommerce.application.repository.OrderRepository;
import com.code.craft.ecommerce.domain.Order;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.createOrder(order);
    }

    public Iterable<Order> getOrders() {
        return orderRepository.getOrders();
    }

}
