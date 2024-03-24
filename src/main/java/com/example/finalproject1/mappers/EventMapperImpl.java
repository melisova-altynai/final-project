package com.example.finalproject1.mappers;

import com.example.finalproject1.dto.CategoryDTO;
import com.example.finalproject1.dto.EventDTO;
import com.example.finalproject1.entities.Category;
import com.example.finalproject1.entities.Event;
import org.springframework.expression.EvaluationContext;
import org.springframework.stereotype.Component;

@Component
public class EventMapperImpl implements EventMapper {
    @Override
    public EventDTO eventToEventDTO(Event event) {
        if (event == null){
            return null;
        }
        EventDTO.EventDTOBuilder eventDTO = EventDTO.builder();
        eventDTO.title(event.getTitle());
        eventDTO.description(event.getDescription());
        eventDTO.date(event.getDate());
        eventDTO.location(event.getLocation());
        eventDTO.category(event.getCategory());
        eventDTO.organizer(event.getOrganizer());
        return eventDTO.build();

    }

    @Override
    public Event eventDTOtoEvent(EventDTO dto) {
        if (dto == null){
            return null;
        }
        Event.EventBuilder event = Event.builder();
        event.title(dto.getTitle());
        event.description(dto.getDescription());
        event.date(dto.getDate());
        event.location(dto.getLocation());
        event.category(dto.getCategory());
        event.organizer(dto.getOrganizer());
        return event.build();
    }


}
