package com.example.GreetingApp.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/console/").permitAll()  // Allow H2 Console access
                        .anyRequest().permitAll()  // Allow all other requests
                )
                .csrf(csrf -> csrf.disable())  // Disable CSRF (needed for Postman & H2 Console)
                .headers(headers -> headers.frameOptions().disable()); // Allow H2 to use iframes

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}