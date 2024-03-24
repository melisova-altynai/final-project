package com.example.finalproject1.dto;

import com.example.finalproject1.entities.Event;
import com.example.finalproject1.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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
    private Set<Event> organizedEvents = new HashSet<>();
    private Set<Event> participatedEvents = new HashSet<>();
}
