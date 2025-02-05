package com.code.craft.ecommerce.infrastructure.adapter;

import com.code.craft.ecommerce.application.repository.OrderProductRepository;
import com.code.craft.ecommerce.domain.Order;
import com.code.craft.ecommerce.domain.OrderProduct;
import com.code.craft.ecommerce.infrastructure.mapper.OrderMapper;
import com.code.craft.ecommerce.infrastructure.mapper.OrderProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderProductRepositoryImpl implements OrderProductRepository {

    private final OrderProductCrudRepository orderProductCrudRepository;
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;

    public OrderProductRepositoryImpl(OrderProductCrudRepository orderProductCrudRepository,
                                      OrderMapper orderMapper,
                                      OrderProductMapper orderProductMapper) {
        this.orderProductCrudRepository = orderProductCrudRepository;
        this.orderMapper = orderMapper;
        this.orderProductMapper = orderProductMapper;
    }

    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return orderProductMapper.toOrderProduct(
                orderProductCrudRepository.save(orderProductMapper.toOrderProductEntity(orderProduct))
        );
    }

    @Override
    public Iterable<OrderProduct> getOrderProducts() {
        return orderProductMapper.toOrderProducts(orderProductCrudRepository.findAll());
    }

    @Override
    public List<OrderProduct> getOrderProductsByOrder(Order order) {
        return orderProductMapper.toOrderProductsList(
                orderProductCrudRepository.findByPkOrderEntity(orderMapper.toOrderEntity(order))
        );
    }
}
