package com.code.craft.ecommerce.application.repository;

import com.code.craft.ecommerce.domain.Order;
import com.code.craft.ecommerce.domain.User;

public interface OrderRepository {
    Order createOrder(Order order);
    Iterable<Order> getOrders();
    Iterable<Order> getOrderByUser(User user);
}
