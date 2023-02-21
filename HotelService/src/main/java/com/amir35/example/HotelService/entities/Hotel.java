package com.amir35.example.HotelService.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "micro_hotels")
public class Hotel {

    @Id
    private String id;

    private String name;

    private String location;

    private String about;
}
