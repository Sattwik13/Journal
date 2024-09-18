package com.journal.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {

    @GetMapping("/health-check")
    public String HealthCheck(){
        return "ok";
    }
}
