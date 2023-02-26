package com.amir35.example.UserService.Impl;

import com.amir35.example.UserService.dto.UserDto;
import com.amir35.example.UserService.entities.Hotel;
import com.amir35.example.UserService.entities.Rating;
import com.amir35.example.UserService.entities.User;
import com.amir35.example.UserService.exceptions.ResourceNotFoundException;
import com.amir35.example.UserService.feign.services.HotelService;
import com.amir35.example.UserService.feign.services.RatingService;
import com.amir35.example.UserService.repositories.UserRepository;
import com.amir35.example.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(UserDto userDto) {
        //Generate unique user id
        String randomUserId = UUID.randomUUID().toString();

        Rating rating = Rating.builder().userId(randomUserId).hotelId(userDto.getHotelId()).
                rating(userDto.getRating()).feedback(userDto.getFeedback()).build();

        ratingService.createRating(rating);
        logger.info("Ratings from user: {}" +rating);

        User user = User.builder().userId(randomUserId).name(userDto.getName()).
                email(userDto.getEmail()).about(userDto.getAbout()).build();

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        List<User> listUser = userRepository.findAll().stream().map(
                user -> {
                //ArrayList<Rating> ratingData = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
                ArrayList<Rating> ratingData = ratingService.getRatingsForAllUser(user.getUserId());
                user.setRatings(ratingData);
                return user;
                }
                ).collect(Collectors.toList());

        return listUser;
    }

    @Override
    public User getUser(String userID) {

        //get user from UserService using UserRepository
        User user = userRepository.findById(userID).orElseThrow(
                () -> new ResourceNotFoundException("User with given id is not found on server !! " +userID));

        //fetch ratings given by the above user from Rating Service
        //http://localhost:8083/ratings/users/userId
        //Rating[] ratingData = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), Rating[].class);
        //Rating[] ratingData = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        //logger.info("{}", ratingData);

        Rating[] ratingData = ratingService.getRatingsForUser(user.getUserId());


        List<Rating> ratings = Arrays.stream(ratingData).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //fetch hotels given according to ratings given from Hotel Service
            //http://localhost:8082/hotels/hotelId
            //ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);

            //commenting next 3 lines to use open feign
            //ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            //Hotel hotel = hotelEntity.getBody();
            //logger.info("Hotels list {} ", hotelEntity.getStatusCode());


            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);


        return user;
    }

    @Override
    public String deleteUser(String userId) {
        userRepository.deleteById(userId);
        return "Successfully Deleted";
    }

    @Override
    public User updateUser(String userId, User user) {
        return userRepository.save(user);
    }
}
