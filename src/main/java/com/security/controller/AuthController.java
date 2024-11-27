package com.security.controller;

import com.security.dto.SignUpRequest;
import com.security.service.UserSignUpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserSignUpService userSignUpService;

    public AuthController(UserSignUpService userSignUpService) {
        this.userSignUpService = userSignUpService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request) {
        userSignUpService.registerUser(request);
        return ResponseEntity.ok("User registered successfully");
    }
}

