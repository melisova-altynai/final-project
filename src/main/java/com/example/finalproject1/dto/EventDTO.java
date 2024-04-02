package com.example.finalproject1.dto;

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

    private CategoryDTO category;
    private UserDTO organizer;
    private Set<UserDTO> participants;

}

