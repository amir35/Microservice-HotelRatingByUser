package com.amir35.example.HotelService.services;

import com.amir35.example.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create
    Hotel createHotel(Hotel hotel);

    //get all hotels
    List<Hotel> getAllHotels();


    //get single hotel
    Hotel getHotel(String id);
}
