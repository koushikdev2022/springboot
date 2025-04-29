package com.koushik.firstproject.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewConClass {
    @GetMapping("/newhello")
    public String sayNewHello() {
        return "under controller";
    }
}
