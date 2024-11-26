package com.security.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String userName;
    private String password;
    private String role;
}
