package com.example.finalproject1.controllers;


import com.example.finalproject1.dto.JwtTokenDto;
import com.example.finalproject1.dto.*;
import com.example.finalproject1.entities.RefreshToken;
import com.example.finalproject1.entities.User;
import com.example.finalproject1.entities.UserRole;
import com.example.finalproject1.exceptions.NotFoundException;
import com.example.finalproject1.repositories.UserRepo;
import com.example.finalproject1.services.JwtService;
import com.example.finalproject1.services.RefreshTokenService;
import com.example.finalproject1.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserRepo userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("login")
    public JwtTokenDto login(@RequestBody AuthLoginDTO loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        if(authentication.isAuthenticated()){
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginDto.getUsername());
            return JwtTokenDto.builder()
                    .accessToken(jwtService.GenerateToken(loginDto.getUsername()))
                    .refreshToken(refreshToken.getToken())
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
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
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @Validated @RequestBody UserDTO userDTO) {
        try{
            userService.updateUser(id, userDTO);
            UserDTO updatedUserDTO = userService.getUserById(id)
                    .orElseThrow(() -> new NotFoundException("Category with id " + id + " not found"));
            return ResponseEntity.ok(updatedUserDTO);
        }catch (NotFoundException e) {
            throw new NotFoundException("User with id " + id + " not found");
        }
    }

    //partially update user by id
    @PatchMapping("/{id}")
    public ResponseEntity<Optional<UserDTO>> partiallyUpdateUser(
            @PathVariable int id,
            @Validated @RequestBody Map<String, Object> updates) {

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

            return ResponseEntity.ok(userOptional);
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
