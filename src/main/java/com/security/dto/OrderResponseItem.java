package com.security.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class OrderResponseItem {
    private String productName;
    private int quantity;
    private BigDecimal totalPrice;
}
