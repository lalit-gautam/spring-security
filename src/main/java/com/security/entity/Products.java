package com.security.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "shop_products")
@Getter
@Builder
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "date_received", nullable = false)
    private LocalDate dateReceived;

    @Column(length = 500)
    private String description;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "supplier", length = 100)
    private String supplier;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;
}
