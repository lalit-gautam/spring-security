package com.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class SecureController {

    @GetMapping("/data") // admin
    public ResponseEntity<String> secureData() {
        return ResponseEntity.ok("This is secured data viewed only by admin");
    }

    @GetMapping("/users/info") // users, admin
    public ResponseEntity<String> userInformation() {
        return ResponseEntity.ok("User information can be viewed by admin and user");
    }

    @GetMapping("/profit") // admin
    public ResponseEntity<String> companyProfit(){
        return ResponseEntity.ok("Company Profit Section");
    }
}
