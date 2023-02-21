package com.amir35.example.RatingService.repositories;

import com.amir35.example.RatingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, String> {
    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);
}
