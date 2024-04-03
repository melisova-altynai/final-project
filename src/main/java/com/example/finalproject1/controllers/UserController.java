package com.example.finalproject1.controllers;


import com.example.finalproject1.dto.UserDTO;
import com.example.finalproject1.entities.User;
import com.example.finalproject1.entities.UserRole;
import com.example.finalproject1.exceptions.NotFoundException;
import com.example.finalproject1.repositories.UserRepo;
import com.example.finalproject1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserRepo userRepository;

    @Autowired
    public UserController(UserService userService, UserRepo userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    //create new user
    @PostMapping
    public ResponseEntity<Void> createUser(@Validated @RequestBody UserDTO userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int id) {
        Optional<UserDTO> userDTO = userService.getUserById(id);
        if (userDTO.isPresent()) {
            return ResponseEntity.ok(userDTO.get());
        } else {
            throw new NotFoundException("User with id " + id + " not found");
        }
    }

    //get all events
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //update user by id
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        try{
            userService.updateUser(id, userDTO);
            return ResponseEntity.ok().build();
        }catch (NotFoundException e) {
            throw new NotFoundException("User with id " + id + " not found");
        }
    }

    //partially update user by id
    @PatchMapping("/{id}")
    public ResponseEntity<Void> partiallyUpdateUser(
            @PathVariable int id,
            @RequestBody Map<String, Object> updates) {

        Optional<UserDTO> userOptional = userService.getUserById(id);

        if (userOptional.isPresent()) {
            UserDTO userDTO = userOptional.get();
            updates.forEach((key, value) -> {
                switch (key) {
                    case "username":
                        userDTO.setUsername((String) value);
                        break;
                    case "password":
                        userDTO.setPassword((String) value);
                        break;
                    case "role":
                        userDTO.setRole(UserRole.valueOf((String) value));
                        break;
                }
            });
            userService.updateUser(id, userDTO);

            return ResponseEntity.ok().build();
        } else {
            throw new NotFoundException("User with id " + id + " not found");
        }
    }

    //delete user by id
    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable int id) {
        Optional<User> optionalCategory = userRepository.findById(id);
        userRepository.deleteById(id);
        return null;
    }
}
