package com.example.finalproject1.dto;

import com.example.finalproject1.entities.User;
import com.example.finalproject1.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EventDTO {
    private int id;
    private String title;
    private String description;
    private String date;
    private String location;
    private Category category;
    private User organizer;
    private Set<User> participants;

}

