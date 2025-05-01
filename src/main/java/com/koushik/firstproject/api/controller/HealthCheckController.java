package com.koushik.firstproject.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HealthCheckController {
    @GetMapping("/health-check")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
        public String healthCheck(){
            return "ok";
        }
}
