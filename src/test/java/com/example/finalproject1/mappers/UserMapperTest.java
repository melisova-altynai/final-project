package com.example.finalproject1.mappers;

import com.example.finalproject1.dto.EventDTO;
import com.example.finalproject1.dto.UserDTO;
import com.example.finalproject1.entities.Category;
import com.example.finalproject1.entities.Event;
import com.example.finalproject1.entities.User;
import com.example.finalproject1.entities.UserRole;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    //Tests the mapping from User entity to UserDTO
    @Test
    public void userToUserDTO() {

        User user = User.builder()
                .username("Joe Doe")
                .password("125466")
                .role(UserRole.ORGANIZER)
                .build();

        UserDTO userDTO = userMapper.userToUserDTO(user);
        assertNotNull(userDTO);

        assertEquals("Joe Doe", userDTO.getUsername());
        assertEquals("125466", userDTO.getPassword());
        assertEquals(UserRole.ORGANIZER, userDTO.getRole());

    }

    //Tests the mapping from Event entity to EventDTO.
    @Test
    public void userDTOtoUser() {

        UserDTO userDTO = UserDTO.builder()
                .username("Joe Doe")
                .password("125466")
                .role(UserRole.ORGANIZER)
                .build();

        User user = userMapper.userDTOtoUser(userDTO);
        assertNotNull(user);

        assertEquals("Joe Doe", user.getUsername());
        assertEquals("125466", user.getPassword());
        assertEquals(UserRole.ORGANIZER, user.getRole());

    }




}