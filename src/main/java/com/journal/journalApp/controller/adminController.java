package com.journal.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.journalApp.Service.userService;
import com.journal.journalApp.entity.userEntry;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/admin")
public class adminController {

    @Autowired
    private userService UserService;


    @GetMapping("/all-users") 
    public ResponseEntity<?> getAllUsers(){
        List<userEntry> all = UserService.getAll();
        if(all != null && !all.isEmpty()) {
           return new ResponseEntity<>(all, HttpStatus.OK);
        }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);     
    }


    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody userEntry user ) {
        UserService.saveAdmin(user);
    }
    
}