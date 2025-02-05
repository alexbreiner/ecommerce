package com.code.craft.ecommerce.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private Integer id;
    private LocalDateTime dateCreated;
    private Integer unitIn;
    private Integer unitOut;
    private String description;
    private Integer balance;
    private Product product;
}
