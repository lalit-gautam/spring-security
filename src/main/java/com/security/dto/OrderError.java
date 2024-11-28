package com.security.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderError {
    private String productName;
    private String message;
}
