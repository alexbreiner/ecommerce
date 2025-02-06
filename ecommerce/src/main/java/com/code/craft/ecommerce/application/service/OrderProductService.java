package com.code.craft.ecommerce.application.service;

import com.code.craft.ecommerce.application.repository.OrderProductRepository;
import com.code.craft.ecommerce.domain.Order;
import com.code.craft.ecommerce.domain.OrderProduct;

import java.util.List;

public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public OrderProduct create(OrderProduct orderProduct) {
        return orderProductRepository.create(orderProduct);
    }

    public Iterable<OrderProduct> getOrderProducts() {
        return orderProductRepository.getOrderProducts();
    }

    public List<OrderProduct> getOrderProductsByOrder(Order order) {
        return orderProductRepository.getOrderProductsByOrder(order);
    }

}
