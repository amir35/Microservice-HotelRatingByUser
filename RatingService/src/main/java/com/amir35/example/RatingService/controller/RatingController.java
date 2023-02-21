package com.amir35.example.RatingService.controller;

import com.amir35.example.RatingService.entities.Rating;
import com.amir35.example.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    //create ratings
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        System.out.println("Rating: "+rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.createRating(rating));
    }

    //get all ratings
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getAllRatings());
    }

    //get all ratings from user id
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByUserId(userId));
    }

    //get all ratings from hotel id
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable String hotelId) {
        return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingByHotelId(hotelId));
    }




}