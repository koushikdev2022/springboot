package com.koushik.firstproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF if it's not needed for your use case
            .authorizeRequests(auth -> auth
                .requestMatchers("/health-check/**", "/user/**").permitAll() // Allow unauthenticated access to these paths
                .anyRequest().authenticated() // Secure other requests
            )
            .formLogin(form -> form.permitAll()) // Enable form login and allow access to login page
            .httpBasic(); // Enable HTTP Basic authentication

        return http.build(); // Build the SecurityFilterChain object
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Password encoder bean for bcrypt encoding
    }
}
