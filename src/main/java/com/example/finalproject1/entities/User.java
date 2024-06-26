package com.example.finalproject1.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;


    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "organizer",cascade = CascadeType.ALL)
    private Set<Event> organizedEvents;

    @ManyToMany(mappedBy = "participants", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Event> participatedEvents  = new HashSet<>();;



}
