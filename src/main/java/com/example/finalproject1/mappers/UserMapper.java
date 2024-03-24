package com.example.finalproject1.mappers;
import com.example.finalproject1.dto.UserDTO;
import com.example.finalproject1.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOtoUser(UserDTO userDTO);
}
