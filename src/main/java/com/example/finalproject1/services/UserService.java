
package com.example.finalproject1.services;

import com.example.finalproject1.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {
    User createUser(User user);
    Optional<User> getUserById(int id);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();

    void updateUser(int id, User user);
}
