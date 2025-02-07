package com.code.craft.ecommerce.infrastructure.adapter;

import com.code.craft.ecommerce.infrastructure.entity.OrderEntity;
import com.code.craft.ecommerce.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderCrudRepository extends CrudRepository<OrderEntity, Integer> {
    public Iterable<OrderEntity> findByUser(UserEntity userEntity);
}
