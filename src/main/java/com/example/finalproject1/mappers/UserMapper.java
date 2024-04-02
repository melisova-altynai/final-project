package com.example.finalproject1.mappers;
import com.example.finalproject1.dto.EventDTO;
import com.example.finalproject1.dto.UserDTO;
import com.example.finalproject1.entities.Event;
import com.example.finalproject1.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOtoUser(UserDTO userDTO);

    @Mapping(target = "organizer", ignore = true)
    @Mapping(target = "participants", ignore = true)
    EventDTO fromEvent(Event event);
}
