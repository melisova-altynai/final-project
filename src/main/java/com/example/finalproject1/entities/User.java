package com.example.finalproject1.entities;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
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
