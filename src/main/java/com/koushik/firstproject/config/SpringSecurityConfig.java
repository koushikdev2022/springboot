package com.koushik.firstproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.koushik.firstproject.exception.CustomAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Autowired
private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF if it's not needed for your use case
            .authorizeRequests(auth -> auth
                .requestMatchers("/health-check/**", "/user/**").permitAll() 
                // .requestMatchers("/admin/**").hasRole("ADMIN")     // for role 1
                // .requestMatchers("/user/**").hasRole("USER")       // for role 2
                // .requestMatchers("/manager/**").hasRole("MANAGER")// Allow unauthenticated access to these paths
                .anyRequest().authenticated() // Secure other requests
            )
            .exceptionHandling(exception -> exception
            .authenticationEntryPoint(customAuthenticationEntryPoint)
        )
            .formLogin(form -> form.permitAll()) // Enable form login and allow access to login page
            .httpBasic(); // Enable HTTP Basic authentication

        return http.build(); // Build the SecurityFilterChain object
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Password encoder bean for bcrypt encoding
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
