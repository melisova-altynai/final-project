package com.example.finalproject1.repositories;

import com.example.finalproject1.entities.Category;
import com.example.finalproject1.entities.Event;
import com.example.finalproject1.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {
    @Autowired
    private CategoryRepo categoryRepository;
    @Test
    public void testFindAll() {
        Category category1 = new Category();
        category1.setName("education");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setName("concert");
        categoryRepository.save(category2);


        List<Category> categories = categoryRepository.findAll();
        assertEquals(5, categories.size());
    }

}