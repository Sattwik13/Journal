package com.journal.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journal.journalApp.Service.userService;
import com.journal.journalApp.Service.weatherService;
import com.journal.journalApp.apiResponse.weatherResponse;
import com.journal.journalApp.entity.userEntry;
import com.journal.journalApp.repository.userRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "User APIs", description = "Read, Update & Delete User")
public class userController {

    @Autowired
    private userService UserService;

    @Autowired
    private userRepository UserRepository;

    @Autowired
    private weatherService WeatherService;

    @GetMapping
    public List<?> getAllUsers(){
        
        return UserService.getAll();
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody userEntry User){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userEntry userInDb = UserService.findByUserName(userName);
        if(userInDb != null) {
            userInDb.setUserName(User.getUserName());
            userInDb.setPassword(User.getPassword());
            UserService.saveNewUser(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestBody userEntry User){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get")
    public ResponseEntity<?> greeting(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        weatherResponse WeatherResponse = WeatherService.getWeather("Pune");
        String greeting = "";
        if(WeatherResponse != null){
            greeting = ", Weather feels like " + WeatherResponse.getCurrent().getFeelslike() + "Deg, Current Humadity " + WeatherResponse.getCurrent().getHumidity() + "%, Visibilty " + WeatherResponse.getCurrent().getVisibility();
        }
        return new ResponseEntity<>("Hi "+ authentication.getName() + greeting, HttpStatus.OK);
    }

}
