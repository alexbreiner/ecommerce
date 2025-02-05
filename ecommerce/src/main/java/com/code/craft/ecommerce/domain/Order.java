package com.code.craft.ecommerce.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private Integer id;
    private LocalDateTime dateCreated;
    private List<OrderProduct> orderProducts;
    private User user;

    public void addOrdersProduct(List<OrderProduct> orderProducts) {
       this.setOrderProducts(orderProducts);
    }

    //Realizar suma total de precios de los productos
    public BigDecimal getTotalOrderPrice() {
        return getOrderProducts()
                .stream()
                .map(OrderProduct::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
