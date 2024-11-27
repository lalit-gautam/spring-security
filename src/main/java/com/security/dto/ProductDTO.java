package com.security.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
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
