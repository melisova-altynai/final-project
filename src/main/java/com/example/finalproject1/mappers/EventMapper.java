package com.example.finalproject1.mappers;
import com.example.finalproject1.dto.EventDTO;
import com.example.finalproject1.entities.Event;
import org.mapstruct.Mapper;


@Mapper
public interface EventMapper {
    EventDTO eventToEventDTO(Event event);
    Event eventDTOtoEvent(EventDTO eventDTO);
}
