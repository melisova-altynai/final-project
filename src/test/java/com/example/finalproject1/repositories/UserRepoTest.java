package com.example.finalproject1.repositories;

import com.example.finalproject1.entities.User;
import com.example.finalproject1.entities.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserRepo userRepository;

    @Test
    public void testFindAll() {
        User user1 = new User();
        user1.setUsername("Iiusf Otrr");
        user1.setPassword("207854");
        user1.setRole(UserRole.PARTICIPANT);
        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("Smdu Oogicus");
        user2.setPassword("539857");
        user2.setRole(UserRole.ORGANIZER);
        userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("Lopi Omgicus");
        user3.setPassword("534527");
        user3.setRole(UserRole.ORGANIZER);
        userRepository.save(user3);

        List<User> users = userRepository.findAll();
        assertEquals(10, users.size());
    }
}