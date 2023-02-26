package com.amir35.example.UserService.feign.services;

import com.amir35.example.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {


    @GetMapping("/ratings/users/{userId}")
    Rating[] getRatingsForUser(@PathVariable("userId") String userId);

    @GetMapping("/ratings/users/{userId}")
    ArrayList<Rating> getRatingsForAllUser(@PathVariable("userId") String userId);

    //Post
    @PostMapping("/ratings")
    public ResponseEntity<Rating> createRating(Rating rating);

    //Put
    @PutMapping("/ratings/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId, Rating rating);

    //Delete
    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);
}
