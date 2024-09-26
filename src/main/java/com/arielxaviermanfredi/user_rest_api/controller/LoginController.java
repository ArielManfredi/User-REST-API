package com.arielxaviermanfredi.user_rest_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arielxaviermanfredi.user_rest_api.service.LoginService;



@RestController
@RequestMapping("/login")
public class LoginController {

    

    @Autowired
    LoginService service;



    
    @GetMapping
    public ResponseEntity<?> get() {
        // return ResponseEntity.ok().body("To log-in in the system, sent a POST request to this URL with your Name and Password or Email and Password.\n Ex.: /login/");
        return ResponseEntity.ok().body("""
                To log-in in the system, sent a POST request to this URL with your Name and Password or Email and Password.\n
                Ex.: http://localhost:8080/login?name=ariel&password=123 or http://localhost:8080/login?email=ariel.xavier.manfredi@gmail.com&password=123
                """);
    }


    @PostMapping
    public ResponseEntity<?> post(String name, String email, String password) {
        if (password==null) {
            return ResponseEntity.badRequest().body("Oops! It looks like you forgot to enter your password. Please provide it to log-in.");
        }
        if (name!=null) {
            try {
                return ResponseEntity.ok().body(service.getUserByNameAndPassword(name, password));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Could not find your User by Name or Password, please check your credencials and the request and try again later.");
            }
        } 
        else if (email!=null) {
            try {
                return ResponseEntity.ok().body(service.getUserByEmailAndPassword(email, password));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("Could not find your User by Email or Password, please check your credencials and the request and try again later.");
            }
        }
        
        return ResponseEntity.ok().body("");
    }
    
    
}
