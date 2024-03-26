package com.example.finalproject1.services;

import com.example.finalproject1.entities.Event;
import com.example.finalproject1.repositories.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepository;

    @Autowired
    public EventServiceImpl(EventRepo eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void createEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public Optional<Event> getEventById(int id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    @Override
    public void updateEvent(int id, Event event) {
        event.setId(id);
        eventRepository.save(event);
    }


}
