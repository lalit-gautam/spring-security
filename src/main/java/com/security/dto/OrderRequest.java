package com.security.dto;

import lombok.Data;

@Data
public class OrderRequest {

    private String productName;
    private int quantity;
}
