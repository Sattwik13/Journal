package com.journal.journalApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.journalApp.Service.userService;
import com.journal.journalApp.entity.userEntry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/public")
public class publicController {

    @Autowired
    private userService UserService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody userEntry user) {
        UserService.saveNewUser(user);
    }
    
    
}
