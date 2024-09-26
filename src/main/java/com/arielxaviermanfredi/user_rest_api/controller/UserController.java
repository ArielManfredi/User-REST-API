package com.arielxaviermanfredi.user_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arielxaviermanfredi.user_rest_api.model.User;
import com.arielxaviermanfredi.user_rest_api.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;




    @PostMapping
    public String addUser(@RequestBody User user) {
        User savedUser = service.saveUser(user);
        if (savedUser != null) {
            return "User "+savedUser.getName()+" saved successfully!";
        }
        return "Could not save the user "+user.getName();
    }


    @GetMapping
    public ResponseEntity<?> getUser(String name, String email) {
        if (name!=null || email!=null) {
            User foundUser = service.getUser(name, email);
            if (foundUser!=null) {
                return ResponseEntity.ok(foundUser);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find the User by the name "+name+" or the email "+email);
        }
        return ResponseEntity.badRequest().body("To get an User, put an Name or Email in a Path Variable, like: http://localhost:8080/user?name=Ariel or http://localhost:8080/user?email=ariel.xavier.manfredi@gmail.com");
    }
    
}
