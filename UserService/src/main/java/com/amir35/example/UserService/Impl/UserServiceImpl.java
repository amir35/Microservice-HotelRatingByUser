package com.amir35.example.UserService.Impl;

import com.amir35.example.UserService.entities.User;
import com.amir35.example.UserService.exceptions.ResourceNotFoundException;
import com.amir35.example.UserService.repositories.UserRepository;
import com.amir35.example.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        //Generate unique user id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userID) {
        return userRepository.findById(userID).orElseThrow(
                () -> new ResourceNotFoundException("User with given id is not found on server !! " +userID));
    }
}
