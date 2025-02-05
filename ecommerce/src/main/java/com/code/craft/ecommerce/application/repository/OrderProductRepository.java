package com.code.craft.ecommerce.application.repository;

import com.code.craft.ecommerce.domain.Order;
import com.code.craft.ecommerce.domain.OrderProduct;

import java.util.List;

public interface OrderProductRepository {
    OrderProduct create(OrderProduct orderProduct);
    Iterable<OrderProduct> getOrderProducts();
    List<OrderProduct> getOrderProductsByOrder(Order order);
}
