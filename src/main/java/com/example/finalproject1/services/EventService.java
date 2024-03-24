package com.example.finalproject1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.finalproject1.repositories.EventRepo;
import com.example.finalproject1.entities.Event;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepo eventRepository;

    @Autowired
    public EventService(EventRepo eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    public Optional<Event> getEventById(int id) {
        return eventRepository.findById(id);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

}

