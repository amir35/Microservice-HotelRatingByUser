package com.amir35.example.UserService.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String userId;
    private String name;
    private String email;
    private String about;
    private int rating;
    private String feedback;
    private String hotelId;
}
