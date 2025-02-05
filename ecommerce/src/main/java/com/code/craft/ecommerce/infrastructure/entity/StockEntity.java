package com.code.craft.ecommerce.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock")
@Data
@NoArgsConstructor
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    private LocalDateTime dateCreated;
    private Integer unitIn;
    private Integer unitOut;
    private String description;
    private Integer balance;

    @ManyToOne
    // Nos indica como gestionar para eliminar la entidad padre
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductEntity productEntity;
}
