package com.arielxaviermanfredi.user_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arielxaviermanfredi.user_rest_api.model.User;
import com.arielxaviermanfredi.user_rest_api.service.LoginService;



@RestController
@RequestMapping("/login")
public class LoginController {

    

    @Autowired
    LoginService service;



    
    @GetMapping
    public ResponseEntity<?> get() {
        // return ResponseEntity.ok().body("To log in the system, sent a POST request to this URL with your Name and Password or Email and Password.\n Ex.: /login/");
        return ResponseEntity.ok().body("""
                To log in the system, send a POST request to this URL with your Name and Password or Email and Password.\n
                Ex.: http://localhost:8080/login?name=ariel&password=123 or http://localhost:8080/login?email=ariel.xavier.manfredi@gmail.com&password=123
                """);
    }


    @PostMapping
    public ResponseEntity<?> post(String name, String email, String password) {
        if (password==null) {
            return ResponseEntity.badRequest().body("Oops! It looks like you forgot to enter your password. Please provide it to log-in.");
        }
        if (name!=null) {
            User user = service.getUserByNameAndPassword(name, password);
            if(user!=null) {
                if (email!=null) {
                    return ResponseEntity.ok().body("Welcome "+user.getName()+", you have successfully logged-in the system using the name and password!\nYou are a cautious person, aren't you? You put both your name and password as well as your email address...");
                }
                return ResponseEntity.ok().body("Welcome "+user.getName()+", you have successfully logged-in the system using the name and password!");
            } 
            return ResponseEntity.badRequest().body("Could not find your User by Name or Password, please check your credentials and the request and try again.");
        } 
        else if (email!=null) {
            User user = service.getUserByEmailAndPassword(email, password);
            if (user!=null) {
                return ResponseEntity.ok().body("Welcome "+user.getName()+", you have successfully logged-in the System using the e-mail and password!");
            }
            return ResponseEntity.badRequest().body("Could not find your User by E-mail and Password, please check your credentials and the request and try again.");
        }
        
        return ResponseEntity.ok().body("You need credentials to log in the system...");
    }
    
    
}
