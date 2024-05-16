package com.example.finalproject1.services;

import com.example.finalproject1.entities.EmailVerification;
import com.example.finalproject1.entities.User;
import com.example.finalproject1.repositories.EmailVerificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;


import java.util.UUID;

@Service
public class VerificationService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailVerificationRepo emailVerificationRepository;

    public void sendVerificationEmail(User user) {
        String token = UUID.randomUUID().toString();

        EmailVerification emailVerification = new EmailVerification();
        emailVerification.setVerificationToken(token);
        emailVerification.setUser(user);
        emailVerificationRepository.save(emailVerification);

        String verificationLink = "http://altynai.melisova@alatoo.edu.kg/verify?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Verify Your Email Address");
        message.setText("Please click the following link to verify your email address: " + verificationLink);

        javaMailSender.send(message);
    }

    public void verifyEmail(String token) {
        EmailVerification emailVerification = emailVerificationRepository.findByVerificationToken(token);

        if (emailVerification != null) {
            emailVerification.setEmailVerified(true);
            emailVerificationRepository.save(emailVerification);
        }
    }

}