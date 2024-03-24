package com.example.finalproject1.bootstrap;

import com.example.finalproject1.services.CategoryService;
import com.example.finalproject1.services.EventService;
import com.example.finalproject1.services.UserService;
import com.example.finalproject1.entities.User;
import com.example.finalproject1.entities.Event;
import com.example.finalproject1.entities.Category;
import com.example.finalproject1.entities.UserRole;

import org.springframework.boot.CommandLineRunner;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    private final UserService userService;
    private final EventService eventService;
    private final CategoryService categoryService;

    @Autowired
    public InitData(UserService userService, EventService eventService, CategoryService categoryService) {
        this.eventService = eventService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadUsers();
        loadEvents();
    }

    private void loadUsers() {
        try {
            ClassPathResource resource = new ClassPathResource("user.csv");
            CSVReader reader = new CSVReader(new FileReader(resource.getFile()));
            String[] line;
            while ((line = reader.readNext()) != null) {
                User user = new User();
                user.setUsername(line[0]);
                user.setPassword(line[1]);
                user.setRole(UserRole.valueOf(line[2]));
                userService.createUser(user);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEvents() {
        try {
            ClassPathResource resource = new ClassPathResource("event.csv");
            CSVReader reader = new CSVReader(new FileReader(resource.getFile()));
            String[] line;
            while ((line = reader.readNext()) != null) {
                Event event = new Event();
                event.setTitle(line[0]);
                event.setDescription(line[1]);
                event.setDateTime(line[2]);
                event.setLocation(line[3]);

                int categoryId = Integer.parseInt(line[4]);
                Optional<Category> optionalCategory = categoryService.getCategoryById(categoryId);
                if (optionalCategory.isPresent()) {
                    event.setCategory(optionalCategory.get());
                }
//                else {
//                    System.err.println("Category not found for event: " + event.getTitle());
//                    continue;
//                }

                int organizerId = Integer.parseInt(line[5]);
                Optional<User> optionalOrganizer = userService.getUserById(organizerId);
                if (optionalOrganizer.isPresent()) {
                    event.setOrganizer(optionalOrganizer.get());
                }
//                else {
//
//                    System.err.println("Organizer not found for event: " + event.getTitle());
//                    continue;
//                }

                eventService.createEvent(event);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadCategories() {
        try {
            ClassPathResource resource = new ClassPathResource("categories.csv");
            CSVReader reader = new CSVReader(new FileReader(resource.getFile()));
            String[] line;
            while ((line = reader.readNext()) != null) {
                Category category = new Category();
                category.setName(line[0]);
                categoryService.createCategory(category);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
