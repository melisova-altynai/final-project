package com.example.finalproject1.services;

import com.example.finalproject1.dto.EventDTO;
import java.util.List;
import java.util.Optional;

public interface EventService {
    // Creates a new event
    EventDTO createEvent(EventDTO eventDTO);
    // Retrieves event by id
    Optional<EventDTO> getEventById(int id);
    // Retrieves all events
    List<EventDTO> getAllEvents();
    // Updates event
    void updateEvent(int id, EventDTO eventDTO);
}
