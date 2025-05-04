package com.example.websport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable())
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/api/users/login", "/api/users/register",
                                                                "/api/courts/**", "/api/users/**",
                                                                "/api/childcourts/**", "/api/bookings/**",
                                                                "/api/booked-courts/**", "/api/products/**",
                                                                "/api/used-products/**", "/api/bills/**",
                                                                "/api/bill/**")

                                                .permitAll()
                                                // Cho phép không cần đăng nhập
                                                .anyRequest().authenticated() // Các request khác cần đăng nhập
                                );
                return http.build();
        }
}
