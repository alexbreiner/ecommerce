package com.code.craft.ecommerce.application.repository;

import com.code.craft.ecommerce.domain.Order;
import com.code.craft.ecommerce.domain.OrderProduct;

import java.util.List;

public interface OrderProductRepository {
    public OrderProduct create(OrderProduct orderProduct);
    public Iterable<OrderProduct> getOrderProducts();
    public List<OrderProduct> getOrderProductsByOrder(Order order);
}
