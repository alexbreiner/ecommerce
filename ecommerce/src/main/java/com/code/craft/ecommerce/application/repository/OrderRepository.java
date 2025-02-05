package com.code.craft.ecommerce.application.repository;

import com.code.craft.ecommerce.domain.Order;

public interface OrderRepository {
    Order createOrder(Order order);
    Iterable<Order> getOrders();
}
