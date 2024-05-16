package com.example.finalproject1.entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
@Entity
public class EmailVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean emailVerified;

    private String verificationToken;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
