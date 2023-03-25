package com.amir35.example.UserService.controller;

import com.amir35.example.UserService.dto.UserDto;
import com.amir35.example.UserService.entities.User;
import com.amir35.example.UserService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //single user
    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //creating fallback method for circuit breaker
    //make sure Return type should be same as that of the calling method --> public ResponseEntity<User> getSingleUser
    //so here it is ResponseEntity<User>
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        logger.info("Fallback is executed because service is down : ", ex.getMessage());

        User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This is dummy user")
                .userId("12345")
                .build();

        return new ResponseEntity<>(user, HttpStatus.OK);
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
