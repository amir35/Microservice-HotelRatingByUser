package com.amir35.example.RatingService.services;

import com.amir35.example.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating createRating(Rating rating);

    //get All ratings
    List<Rating> getAllRatings();

    //get all by user id
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel id
    List<Rating> getRatingByHotelId(String hotelId);
}
