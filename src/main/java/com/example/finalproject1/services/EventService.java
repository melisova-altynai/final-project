package com.example.finalproject1.services;

import com.example.finalproject1.dto.EventDTO;
import java.util.List;
import java.util.Optional;

public interface EventService {
    EventDTO createEvent(EventDTO eventDTO);
    Optional<EventDTO> getEventById(int id);
    List<EventDTO> getAllEvents();
    void updateEvent(int id, EventDTO eventDTO);
}
