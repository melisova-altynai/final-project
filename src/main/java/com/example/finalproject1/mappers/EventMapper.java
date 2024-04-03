package com.example.finalproject1.mappers;
import com.example.finalproject1.dto.CategoryDTO;
import com.example.finalproject1.dto.EventDTO;
import com.example.finalproject1.dto.UserDTO;
import com.example.finalproject1.entities.Category;
import com.example.finalproject1.entities.Event;
import com.example.finalproject1.entities.User;
import org.mapstruct.Mapper;


@Mapper
public interface EventMapper {

    // Converts event entity to eventDTO
    EventDTO eventToEventDTO(Event event);
    // Converts eventDTO to event entity
    Event eventDTOtoEvent(EventDTO eventDTO);


}
