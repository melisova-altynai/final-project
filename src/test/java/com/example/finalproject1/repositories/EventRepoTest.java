package com.example.finalproject1.repositories;

import com.example.finalproject1.entities.Event;
import com.example.finalproject1.entities.User;
import com.example.finalproject1.entities.Category;
import com.example.finalproject1.entities.UserRole;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventRepoTest {

    @Autowired
    private EventRepo eventRepository;

    @Inject
    private UserRepo userRepository;
    @Inject
    private CategoryRepo categoryRepository;
    @Test
    public void testFindAll() {

        Event event1 = new Event();
        event1.setTitle("Silver Slugger Award");
        event1.setDescription("The coach was ejected for arguing with the umpire.");
        event1.setDateTime("3/29/2023");
        event1.setLocation("Chesapeake");

        Category musicCategory = new Category();
        musicCategory.setName("music");
        Category persistedCategory = categoryRepository.save(musicCategory);
        event1.setCategory(persistedCategory);

        User organizer = userRepository.findById(12).orElse(null);
        event1.setOrganizer(organizer);
        eventRepository.save(event1);

        // =============================================== //

        Event event2 = new Event();
        event2.setTitle("Silver Slugger Award");
        event2.setDescription("The coach was ejected for arguing with the umpire.");
        event2.setDateTime("3/29/2023");
        event2.setLocation("Chesapeake");

        Category sportsCategory = new Category();
        sportsCategory.setName("music");
        Category persistedCategory1 = categoryRepository.save(sportsCategory);
        event1.setCategory(persistedCategory1);
        User organizer1 = userRepository.findById(45).orElse(null);
        event1.setOrganizer(organizer1);
        eventRepository.save(event2);


        List<Event> events = eventRepository.findAll();
        assertEquals(202, events.size());
    }

}