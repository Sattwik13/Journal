package com.journal.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.journalApp.Service.userService;
import com.journal.journalApp.entity.userEntry;

@RestController
@RequestMapping("/user")
public class userController {

    @Autowired
    private userService UserService;

    @GetMapping
    public List<?> getAllUsers(){
        
        return UserService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody userEntry User) {

        UserService.saveEntry(User);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody userEntry User){

        userEntry userInDb = UserService.findByUserName(User.getUserName());
        if(userInDb != null) {
            userInDb.setUserName(User.getUserName());
            userInDb.setPassword(User.getPassword());
            UserService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
