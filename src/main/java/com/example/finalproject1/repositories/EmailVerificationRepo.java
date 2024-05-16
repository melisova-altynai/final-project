package com.example.finalproject1.repositories;

import com.example.finalproject1.entities.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationRepo extends JpaRepository<EmailVerification, Long> {
    EmailVerification findByVerificationToken(String token);
}