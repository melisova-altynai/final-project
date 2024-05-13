package com.example.finalproject1.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AuthLoginDTO {
    private String username;
    private String password;
}
