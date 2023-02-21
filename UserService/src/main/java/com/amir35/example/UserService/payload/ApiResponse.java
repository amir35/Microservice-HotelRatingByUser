package com.amir35.example.UserService.payload;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus status;
}
