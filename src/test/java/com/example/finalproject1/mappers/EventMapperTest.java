package com.example.finalproject1.mappers;

import com.example.finalproject1.dto.CategoryDTO;
import com.example.finalproject1.dto.EventDTO;
import com.example.finalproject1.dto.UserDTO;
import com.example.finalproject1.entities.Category;
import com.example.finalproject1.entities.Event;
import com.example.finalproject1.entities.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class EventMapperTest {
    private final EventMapper eventMapper = Mappers.getMapper(EventMapper.class);

    @Test
    public void eventToEventDTO() {

        User organizer1 = User.builder()
                .username("Joe Doe")
                .build();
        Category category1 = Category.builder()
                .name("Test Category")
                .build();

        Event event = Event.builder()
                        .title("Test Event")
                        .description("This is a test event")
                        .location("Bishkek")
                        .date("03/12/2023")
                        .organizer(organizer1)
                        .category(category1)
                        .build();

        EventDTO eventDTO = eventMapper.eventToEventDTO(event);
        assertNotNull(eventDTO);

        assertEquals("Test Event", eventDTO.getTitle());
        assertEquals("This is a test event", eventDTO.getDescription());
        assertEquals("Bishkek", eventDTO.getLocation());
        assertEquals("03/12/2023", eventDTO.getDate());

        assertNotNull(eventDTO.getOrganizer());
        assertEquals("Joe Doe", eventDTO.getOrganizer().getUsername());
        assertNotNull(eventDTO.getCategory());
        assertEquals("Test Category", eventDTO.getCategory().getName());

    }

    @Test
    public void eventDTOtoEvent() {

        UserDTO organizer1 = UserDTO.builder()
                .username("John Doe")
                .build();
        CategoryDTO category1 = CategoryDTO.builder()
                .name("Test Category")
                .build();

        EventDTO eventDTO = EventDTO.builder()
                .title("Test Event")
                .description("This is a test event")
                .location("Bishkek")
                .date("03/12/2023")
                .organizer(organizer1)
                .category(category1)
                .build();

        Event event = eventMapper.eventDTOtoEvent(eventDTO);
        assertNotNull(event);

        assertEquals("Test Event", event.getTitle());
        assertEquals("This is a test event", event.getDescription());
        assertEquals("Bishkek", event.getLocation());
        assertEquals("03/12/2023", event.getDate());

        assertNotNull(event.getOrganizer());
        assertEquals("John Doe", event.getOrganizer().getUsername());
        assertNotNull(event.getCategory());
        assertEquals("Test Category", event.getCategory().getName());


    }
}