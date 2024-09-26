package com.arielxaviermanfredi.user_rest_api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        User existentUser = service.getUserFull(user.getName(), user.getEmail(), user.getPassword());

        if (existentUser!=null) {
            return "User already registered!";
        }

        if(user.getPassword()==null){
            return "Can not save the user without a password!";
        }

        User savedUser = service.saveUser(user);

        if (savedUser != null) {
            return "User "+savedUser.getName()+" saved successfully!\n" + savedUser;
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


    @PutMapping
    public String editUser(@RequestBody User user) {
        User existentUser = service.getUserFull(user.getName(), user.getEmail(), user.getPassword());

        if (existentUser!=null) { return "User credentials is identical the registered one."; }

        if(user.getPassword()==null){ return "Can't save the user without a password."; }

        if (user.getId()==null) { return "Can't edit the user without an ID."; }

        User getByIdUser = service.getUserById(user.getId());

        if (getByIdUser!=null) {
            getByIdUser.setName(user.getName()!=getByIdUser.getName() ? user.getName() : getByIdUser.getName());
            getByIdUser.setEmail(user.getEmail()!=getByIdUser.getEmail() ? user.getEmail() : getByIdUser.getEmail());
            getByIdUser.setPassword(user.getPassword()!=getByIdUser.getPassword() ? user.getPassword() : getByIdUser.getPassword());

            User savedUser = service.saveUser(user);
    
            if (savedUser != null) {
                return "User "+savedUser.getName()+" edited successfully!\n" + savedUser;
            }
        }

        return "Could not edit the user "+user.getName()+". Provide the id and ";
    }

    @DeleteMapping
    public String deleteUser(UUID id) {
        System.out.println("ID: "+id);
        if (id==null) { return "Can't delete an user without an Id..."; }

        String deleteMessage = service.deleteUser(id);

        return deleteMessage!=null ? deleteMessage : "Could not delete the user of Id \""+id +"\" or inexistent Id.";
    }
    
}
