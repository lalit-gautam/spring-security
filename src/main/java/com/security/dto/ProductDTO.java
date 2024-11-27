package com.security.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class ProductDTO {

    private String name;
    private BigDecimal price;
    private int quantity;
    private LocalDate dateReceived;
    private String description;
    private String category;
    private String supplier;
    private boolean isActive;
}
