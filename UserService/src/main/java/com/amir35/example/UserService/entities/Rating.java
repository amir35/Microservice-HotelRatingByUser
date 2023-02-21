package com.amir35.example.UserService.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {

    @Id
    private String ratingId;

    private String userId;

    private String hotelId;

    private int rating;

    private String feedback;

    private Hotel hotel;
}