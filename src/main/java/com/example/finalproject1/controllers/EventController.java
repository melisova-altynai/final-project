package com.example.finalproject1.controllers;

import com.example.finalproject1.dto.EventDTO;
import com.example.finalproject1.entities.Event;
import com.example.finalproject1.exceptions.NotFoundException;
import com.example.finalproject1.repositories.EventRepo;
import com.example.finalproject1.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/events")
public class EventController {

    private final EventService eventService;
    private final EventRepo eventRepository;


    @Autowired
    public EventController(EventService eventService, EventRepo eventRepository) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
    }

    //create new event
    @PostMapping(" ")
    public ResponseEntity<EventDTO> createEvent(@Validated @RequestBody EventDTO eventDTO) {
        EventDTO savedEventDTO = eventService.createEvent(eventDTO);
        return ResponseEntity
                .created(URI.create("/api/v1/event" + savedEventDTO.getId()))
                .body(savedEventDTO);
    }

    //get event by id
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable int id) {
        Optional<EventDTO> eventDTO = eventService.getEventById(id);
        if (eventDTO.isPresent()) {
            return ResponseEntity.ok(eventDTO.get());
        } else {
            throw new NotFoundException("Event with id " + id + " not found");
        }
    }

    //get all events
    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    //update event by id
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEvent(@PathVariable int id, @Validated @RequestBody EventDTO eventDTO) {
        try{
            eventService.updateEvent(id, eventDTO);
            return ResponseEntity.ok().build();
        }catch (NotFoundException e) {
            throw new NotFoundException("Event with id " + id + " not found");
        }
    }

    //partially event by id
    @PatchMapping("/{id}")
    public ResponseEntity<Void> partiallyUpdateCategory(
            @PathVariable int id,
            @Validated @RequestBody Map<String, Object> updates) {

        Optional<EventDTO> eventOptional = eventService.getEventById(id);

        if (eventOptional.isPresent()) {
            EventDTO eventDTO = eventOptional.get();
            updates.forEach((key, value) -> {
                switch (key) {
                    case "title":
                        eventDTO.setTitle((String) value);
                        break;
                    case "description":
                        eventDTO.setDescription((String) value);
                        break;
                    case "date":
                        eventDTO.setDate((String) value);
                        break;
                    case "location":
                        eventDTO.setLocation((String) value);
                        break;
                }
            });
            eventService.updateEvent(id, eventDTO);

            return ResponseEntity.ok().build();
        } else {
            throw new NotFoundException("Event with id " + id + " not found");
        }
    }

    //delete event by id
    @DeleteMapping("{id}")
    public EventDTO deleteEvent(@PathVariable int id) {
        Optional<Event> optionalBook = eventRepository.findById(id);
        eventRepository.deleteById(id);
        return null;
    }


}
