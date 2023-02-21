package com.amir35.example.UserService.services;

import com.amir35.example.UserService.entities.User;

import java.util.List;

public interface UserService {

    //create user
    User saveUser(User user);

    //get all users
    List<User> getAllUsers();

    //get Single user of given user id
    User getUser(String userID);

    //Delete

    //Update


}
