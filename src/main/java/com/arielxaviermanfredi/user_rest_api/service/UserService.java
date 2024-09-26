package com.arielxaviermanfredi.user_rest_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arielxaviermanfredi.user_rest_api.model.User;
import com.arielxaviermanfredi.user_rest_api.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    
    public User saveUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
    }


    public User getUser(String name, String email) {
        try {
            return userRepository.findByNameIgnoreCaseOrEmailIgnoreCase(name, email);
        } catch (Exception e) {
            return null;
        }
    }
}
