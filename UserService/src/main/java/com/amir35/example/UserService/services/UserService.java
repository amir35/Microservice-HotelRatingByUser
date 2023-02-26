package com.amir35.example.UserService.services;

import com.amir35.example.UserService.dto.UserDto;
import com.amir35.example.UserService.entities.User;

import java.util.List;

public interface UserService {

    //create user
    User saveUser(UserDto userDto);

    //get all users
    List<User> getAllUsers();

    //get Single user of given user id
    User getUser(String userID);

    //Delete
    String deleteUser(String userId);

    //Update
    User updateUser(String userId, User user);


}
