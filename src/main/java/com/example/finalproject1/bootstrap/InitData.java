package com.example.finalproject1.bootstrap;

import com.example.finalproject1.entities.Category;
import com.example.finalproject1.entities.Event;
import com.example.finalproject1.entities.User;
import com.example.finalproject1.repositories.CategoryRepo;
import com.example.finalproject1.repositories.EventRepo;
import com.example.finalproject1.repositories.UserRepo;
import com.example.finalproject1.entities.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final CategoryRepo categoryRepository;
    private final UserRepo userRepository;
    private final EventRepo eventRepository;

    @Override
    public void run(String... args) throws Exception {
        Category category1 = new Category(1, "music", new HashSet<>());
        Category category2 = new Category(2,"sports", new HashSet<>());
        Category category3 = new Category(3, "music2", new HashSet<>());

        categoryRepository.saveAll(List.of(category1, category2, category3));

        User user3 = new User(3,"Aidana", "23455", "example@com", UserRole.ORGANIZER, new HashSet<>(), null);
        User user1 = new User(1,"Aidin", "123566", "example2@gmail.com", UserRole.ORGANIZER, new HashSet<>(),null);
        User user2 = new User(2,"Altynai","789456","example3@gmail.com",UserRole.PARTICIPANT,null,new HashSet<>());
        User user5 = new User(5,"Atai", "789415", "example4@gmail.com",UserRole.PARTICIPANT,null, new HashSet<>());
        User user6 = new User(6,"Samira","124563","example5@gmail.com",UserRole.PARTICIPANT, null, new HashSet<>());
        User user7 = new User(7,"Umar","789456","example6@gmail.com",UserRole.PARTICIPANT, null, new HashSet<>());
        User user4 = new User(4,"Madina", "124545","example7@gmail.com", UserRole.ORGANIZER, new HashSet<>(),null);

        userRepository.saveAll(List.of(user1, user2, user3, user4, user5, user6, user7));

        Event event1 = new Event(1,"Event1", "We invite you to music party", "03/12/2023", "Bishkek", category1, user3, Set.of(user2) );
        Event event2 = new Event(2,"Event2","Sport competition", "02/11/2024", "Osh", category3, user4, Set.of(user7));
        Event event3 = new Event(3,"Event3", "Art meeting", "04/02/2024", "Naryn", category1, user1, Set.of(user6, user5));

        eventRepository.saveAll(List.of(event1, event2, event3));
    }

}

