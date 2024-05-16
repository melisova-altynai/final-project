package com.example.finalproject1.controllers;

import com.example.finalproject1.dto.*;
import com.example.finalproject1.entities.RefreshToken;
import com.example.finalproject1.entities.User;
import com.example.finalproject1.entities.UserRole;
import com.example.finalproject1.repositories.UserRepo;
import com.example.finalproject1.services.JwtService;
import com.example.finalproject1.services.RefreshTokenService;
//import com.example.finalproject1.services.VerificationService;
import com.example.finalproject1.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepository;
//    private final VerificationService verificationService;
    private final UserService userService;



    @PostMapping("token")
    public JwtTokenDto jwtLogin(@RequestBody AuthLoginDTO loginDto){
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


    @PostMapping("refreshToken")
    public JwtTokenDto refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(userInfo -> {
                    String accessToken = jwtService.GenerateToken(userInfo.getUsername());
                    return JwtTokenDto.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequestDTO.getRefreshToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB!!"));
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody AuthLoginDTO authLoginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authLoginDTO.getUsername(),
                        authLoginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("user signed success!", HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        if(userRepository.existsByUsername(registerDTO.getUsername())){
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode((registerDTO.getPassword())));
        user.setEmail(registerDTO.getEmail());

        userRepository.save(user);

        return new ResponseEntity<>("user registered success!", HttpStatus.OK);
    }

//    @GetMapping("verify")
//    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
//        verificationService.verifyEmail(token);
//        return ResponseEntity.ok("Email verified successfully.");
//    }



}
