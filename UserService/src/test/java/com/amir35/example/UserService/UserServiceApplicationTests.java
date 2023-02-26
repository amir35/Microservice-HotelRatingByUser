package com.amir35.example.UserService;

import com.amir35.example.UserService.entities.Rating;
import com.amir35.example.UserService.feign.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

	@Test
	void createRating() {
		Rating rating = Rating.builder().
				rating(10).userId("").hotelId("").feedback("This is created using Feign client").build();

		Rating saveRating = ratingService.createRating(rating).getBody();

		System.out.println("New rating created");

	}
}
