package com.example.finalproject1.services;

import com.example.finalproject1.dto.UserDTO;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    Optional<UserDTO> getUserById(int id);
    List<UserDTO> getAllUsers();
    void updateUser(int id, UserDTO userDTO);
}
