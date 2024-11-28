package com.security.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestList {
    private List<OrderRequest> orders;
}
