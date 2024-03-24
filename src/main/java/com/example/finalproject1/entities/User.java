package com.example.finalproject1.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
    private Set<Event> organizedEvents = new HashSet<>();

    @ManyToMany(mappedBy = "participants")
    private Set<Event> participatedEvents = new HashSet<>();

}
