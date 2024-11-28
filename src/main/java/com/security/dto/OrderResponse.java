package com.security.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderResponse {
    List<OrderResponseItem> orderResponseItems;
    private int totalItems;
    private BigDecimal overallPrice;
}
