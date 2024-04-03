package com.example.finalproject1.services;

import com.example.finalproject1.dto.UserDTO;
import java.util.List;
import java.util.Optional;

public interface UserService {
    // Creates a new user
    UserDTO createUser(UserDTO userDTO);
    // Retrieves user by id
    Optional<UserDTO> getUserById(int id);
    // Retrieves all users
    List<UserDTO> getAllUsers();
    // Updates user
    void updateUser(int id, UserDTO userDTO);
}
