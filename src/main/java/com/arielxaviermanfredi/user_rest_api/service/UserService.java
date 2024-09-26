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
        User foundUser = userRepository.findByNameIgnoreCaseOrEmailIgnoreCase(name, email);

        if(foundUser!=null) {
            return foundUser;
        }
        return null;
    }

    public User getUserFull(String name, String email, String password) {
        User foundUser = userRepository.findByNameIgnoreCaseAndEmailIgnoreCaseAndPassword(name, email, password);

        if(foundUser!=null) {
            return foundUser;
        }
        return null;
    }
}
