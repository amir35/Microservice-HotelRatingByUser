package com.amir35.example.HotelService.impl;

import com.amir35.example.HotelService.entities.Hotel;
import com.amir35.example.HotelService.exception.ResourceNotFoundException;
import com.amir35.example.HotelService.repositories.HotelRepository;
import com.amir35.example.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Hotel with given id not available"));
    }
}
