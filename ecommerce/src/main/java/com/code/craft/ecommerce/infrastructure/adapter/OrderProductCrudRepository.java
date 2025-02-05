package com.code.craft.ecommerce.infrastructure.adapter;

import com.code.craft.ecommerce.infrastructure.entity.OrderEntity;
import com.code.craft.ecommerce.infrastructure.entity.OrderProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderProductCrudRepository extends CrudRepository<OrderProductEntity, Integer> {
    List<OrderProductEntity> findByPkOrderEntity(OrderEntity orderEntity);

}
