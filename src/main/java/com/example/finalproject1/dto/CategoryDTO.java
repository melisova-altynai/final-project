package com.example.finalproject1.dto;

import jakarta.persistence.Entity;
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
public class CategoryDTO {
    private int id;
    private String name;
    private Set<EventDTO> events = new HashSet<>();


}
