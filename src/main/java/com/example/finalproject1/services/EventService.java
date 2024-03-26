package com.example.finalproject1.services;

import com.example.finalproject1.entities.Event;
import java.util.List;
import java.util.Optional;

public interface EventService {
    void createEvent(Event event);
    Optional<Event> getEventById(int id);
    List<Event> getAllEvents();

    void updateEvent(int id, Event event);
}
