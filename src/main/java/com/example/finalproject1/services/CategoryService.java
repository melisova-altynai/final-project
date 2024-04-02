package com.example.finalproject1.services;

import com.example.finalproject1.dto.CategoryDTO;
import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void createCategory(CategoryDTO categoryDTO);
    Optional<CategoryDTO> getCategoryById(int id);
    List<CategoryDTO> getAllCategories();
    void updateCategory(int id, CategoryDTO categoryDTO);
}
