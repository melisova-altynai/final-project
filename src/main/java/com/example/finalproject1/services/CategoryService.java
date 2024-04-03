package com.example.finalproject1.services;

import com.example.finalproject1.dto.CategoryDTO;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    // Creates a new category
    void createCategory(CategoryDTO categoryDTO);
    // Retrieves category by id
    Optional<CategoryDTO> getCategoryById(int id);
    // Retrieves all categories
    List<CategoryDTO> getAllCategories();
    // Updates category
    void updateCategory(int id, CategoryDTO categoryDTO);
}
