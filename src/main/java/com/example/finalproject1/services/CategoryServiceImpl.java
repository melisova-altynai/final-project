package com.example.finalproject1.services;

import com.example.finalproject1.dto.CategoryDTO;
import com.example.finalproject1.entities.Category;
import com.example.finalproject1.exceptions.NotFoundException;
import com.example.finalproject1.mappers.CategoryMapper;
import com.example.finalproject1.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.categoryDTOtoCategory(categoryDTO);
        categoryRepository.save(category);
    }

    @Override
    public Optional<CategoryDTO> getCategoryById(int id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.map(categoryMapper::categorytoCategoryDTO);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::categorytoCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateCategory(int id, CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category updatedCategory = categoryMapper.categoryDTOtoCategory(categoryDTO);
            updatedCategory.setId(id);
            categoryRepository.save(updatedCategory);
        } else {
            throw new NotFoundException("Category with id " + id + " not found");
        }
    }

}
