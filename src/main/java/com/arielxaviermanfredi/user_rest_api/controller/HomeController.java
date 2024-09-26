package com.arielxaviermanfredi.user_rest_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class HomeController {
    
    @GetMapping
    public String welcome() {
        return "\"Welcome to the User REST API, I hope u enjoy it!\" \n by Ariel Xavier Manfredi";
    }
    
}
