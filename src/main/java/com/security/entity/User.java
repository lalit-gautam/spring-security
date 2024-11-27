package com.security.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users_info")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;
}
