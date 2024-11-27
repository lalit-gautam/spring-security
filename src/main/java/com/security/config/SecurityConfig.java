package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * IF URL http://localhost:8080/api/secure/admin/product/add (POST) throws 401 or 403 during request disable CSRF for that url.
 * CSRF tokens are used to prevent cross-site request forgery attacks. When CSRF is enabled,
 * Spring Security expects a valid CSRF token with each state-changing request (e.g., POST, PUT, DELETE).
 * If the token is missing or invalid, the server responds with a 403 Forbidden.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/auth/signup", "/api/secure/admin/**"))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/signup").permitAll()
                        .requestMatchers("/api/secure/users/**").hasAnyRole("USER", "ADMIN") // USER (and ADMIN) can access this path
                        .requestMatchers("/api/secure/admin/**").hasRole("ADMIN") // ADMIN can access any path under /api/secure
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
