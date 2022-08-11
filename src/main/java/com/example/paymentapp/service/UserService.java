package com.example.paymentapp.service;

import com.example.paymentapp.model.User;
import com.example.paymentapp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
