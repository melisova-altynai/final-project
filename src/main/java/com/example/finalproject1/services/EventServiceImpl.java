package com.example.finalproject1.services;

import com.example.finalproject1.dto.EventDTO;
import com.example.finalproject1.entities.Event;
import com.example.finalproject1.mappers.EventMapper;
import com.example.finalproject1.repositories.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventServiceImpl(EventRepo eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }


    @Override
    public EventDTO createEvent(EventDTO event) {
        return eventMapper.eventToEventDTO(
                eventRepository.save(eventMapper.eventDTOtoEvent(event))
        );
    }
    @Override
    public Optional<EventDTO> getEventById(int id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        return eventOptional.map(eventMapper::eventToEventDTO);
    }

    @Override
    public List<EventDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(eventMapper::eventToEventDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateEvent(int id, EventDTO eventDTO) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        eventOptional.ifPresent(event -> {
            Event updatedEvent = eventMapper.eventDTOtoEvent(eventDTO);
            updatedEvent.setId(id);
            eventRepository.save(updatedEvent);
        });
    }
}
