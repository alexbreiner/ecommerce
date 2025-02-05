package com.code.craft.ecommerce.infrastructure.adapter;

import com.code.craft.ecommerce.infrastructure.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderCrudRepository extends CrudRepository<OrderEntity, Integer> {
}
