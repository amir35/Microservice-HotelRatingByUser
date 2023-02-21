package com.amir35.example.UserService.repositories;

import com.amir35.example.UserService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
