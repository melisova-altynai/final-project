package com.example.finalproject1.controllers;

import com.example.finalproject1.dto.EventDTO;
import com.example.finalproject1.entities.Event;
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

    @PostMapping()
    public ResponseEntity<EventDTO> createEvent(@Validated @RequestBody EventDTO eventDTO) {
        EventDTO savedEventDTO = eventService.createEvent(eventDTO);
        return ResponseEntity
                .created(URI.create("/api/v1/event/" + savedEventDTO.getId()))
                .body(savedEventDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable int id) {
        Optional<EventDTO> eventDTO = eventService.getEventById(id);
        return eventDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<EventDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEvent(@PathVariable int id, @RequestBody EventDTO eventDTO) {
        eventService.updateEvent(id, eventDTO);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> partiallyUpdateCategory(
            @PathVariable int id,
            @RequestBody Map<String, Object> updates) {

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
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public EventDTO deleteEvent(@PathVariable int id) {
        Optional<Event> optionalBook = eventRepository.findById(id);
        eventRepository.deleteById(id);
        return null;
    }


}
