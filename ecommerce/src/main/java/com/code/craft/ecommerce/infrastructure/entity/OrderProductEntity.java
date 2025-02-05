package com.code.craft.ecommerce.infrastructure.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ordersproducts")
public class OrderProductEntity {
    @EmbeddedId
    private OrderProductPK pk;
    private Integer quantity;
}
