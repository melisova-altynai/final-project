package com.example.finalproject1.services;

import com.example.finalproject1.entities.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void createCategory(Category category);
    Optional<Category> getCategoryById(int id);
    List<Category> getAllCategories();
    void updateCategory(int id, Category category);

}
