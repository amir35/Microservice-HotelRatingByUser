package com.amir35.example.RatingService.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "micro_ratings")
public class Rating {

    @Id
    private String ratingId;

    private String userId;

    private String hotelId;

    private int rating;

    private String feedback;
}
