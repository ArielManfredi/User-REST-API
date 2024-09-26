package com.arielxaviermanfredi.user_rest_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.arielxaviermanfredi.user_rest_api.model.User;
import com.arielxaviermanfredi.user_rest_api.repository.UserRepository;

@Service
public class LoginService {


    @Autowired
    UserRepository userRepository;


    
    public User getUserByNameAndPassword(@NonNull String name,@NonNull String password) {
        try {
            return userRepository.findByNameIgnoreCaseAndPassword(name, password);
        } catch (Exception e) {
            return null;
        }
    }
    
    public User getUserByEmailAndPassword(@NonNull String email,@NonNull String password) {
        try {
            return userRepository.findByEmailIgnoreCaseAndPassword(email, password);
        } catch (Exception e) {
            return null;
        }
    }
}
