package com.amir35.example.UserService.controller;

import com.amir35.example.UserService.dto.UserDto;
import com.amir35.example.UserService.entities.User;
import com.amir35.example.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //single user
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }


    //all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUser = userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }

    //Put mapping
    @PostMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user){
        User user1 = userService.updateUser(userId, user);
        return ResponseEntity.status(HttpStatus.OK).body(user1);
    }

    //delete
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return userService.deleteUser(userId);
    }
}
