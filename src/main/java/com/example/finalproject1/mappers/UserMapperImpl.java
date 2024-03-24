package com.example.finalproject1.mappers;

import com.example.finalproject1.dto.UserDTO;
import com.example.finalproject1.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{
    @Override
    public UserDTO userToUserDTO(User user) {
        if (user == null){
            return null;
        }
        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();
        userDTO.username(user.getUsername());
        userDTO.password(user.getPassword());
        userDTO.role(user.getRole());
        return userDTO.build();
    }

    @Override
    public User userDTOtoUser(UserDTO dto) {
        if(dto == null){
            return null;
        }
        User.UserBuilder user = User.builder();
        user.username(dto.getUsername());
        user.password(dto.getPassword());
        user.role(dto.getRole());
        return user.build();

    }

}
