package com.code.craft.ecommerce.infrastructure.adapter;

import com.code.craft.ecommerce.domain.Product;
import com.code.craft.ecommerce.infrastructure.entity.ProductEntity;
import com.code.craft.ecommerce.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductCrudRepository extends CrudRepository<ProductEntity, Integer> {
    Iterable<ProductEntity> findByUserEntity(UserEntity userEntity);

}
