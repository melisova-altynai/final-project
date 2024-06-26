package com.example.finalproject1.dto;

import com.example.finalproject1.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private UserRole role;
    private Set<EventDTO> organizedEvents;
    private Set<EventDTO> participatedEvents;
}
