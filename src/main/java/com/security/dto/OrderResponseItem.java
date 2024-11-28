package com.security.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class OrderResponseItem {
    private String orderName;
    private int quantityRequested;
    private BigDecimal price;
    private boolean isAvailable;
    private boolean isOutOfStock;
}
