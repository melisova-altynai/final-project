package com.example.finalproject1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterDTO {
    private String username;
    private String password;
    private String email;

}
