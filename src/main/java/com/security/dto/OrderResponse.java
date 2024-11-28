package com.security.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class OrderResponse {
    private List<OrderResponseItem> items;
    private List<OrderError> errors;
    private int totalItems;
    private BigDecimal overallTotalPrice;
}
