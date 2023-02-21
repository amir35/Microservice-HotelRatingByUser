package com.amir35.example.HotelService.repositories;

import com.amir35.example.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {
}
