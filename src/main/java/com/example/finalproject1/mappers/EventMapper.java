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

    EventDTO eventToEventDTO(Event event);
    Event eventDTOtoEvent(EventDTO eventDTO);

    CategoryDTO categorytoCategoryDTO(Category category);

    Category categoryDTOtoCategory(CategoryDTO dto);

    UserDTO userToUserDTO(User user);

    User userDTOtoUser(UserDTO dto);

}
