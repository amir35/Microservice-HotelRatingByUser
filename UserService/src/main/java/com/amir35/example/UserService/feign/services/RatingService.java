package com.amir35.example.UserService.feign.services;

import com.amir35.example.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {


    @GetMapping("/ratings/users/{userId}")
    Rating[] getRatingsForUser(@PathVariable("userId") String userId);

    @GetMapping("/ratings/users/{userId}")
    ArrayList<Rating> getRatingsForAllUser(@PathVariable("userId") String userId);
}
