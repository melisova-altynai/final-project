package com.example.finalproject1.controllers;

import com.example.finalproject1.dto.CategoryDTO;
import java.util.Optional;
import com.example.finalproject1.entities.Category;
import com.example.finalproject1.exceptions.NotFoundException;
import com.example.finalproject1.repositories.CategoryRepo;
import com.example.finalproject1.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryRepo categoryRepository;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryRepo categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    // Create new category
    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //get category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable int id) {
        Optional<CategoryDTO> categoryDTO = categoryService.getCategoryById(id);
        if (categoryDTO.isPresent()) {
            return ResponseEntity.ok(categoryDTO.get());
        } else {
            throw new NotFoundException("Category with id " + id + " not found");
        }
    }

    //get all categories
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    //update category by id
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable int id,@RequestBody CategoryDTO categoryDTO) {
        try{
            categoryService.updateCategory(id, categoryDTO);
            return ResponseEntity.ok().build();
        }catch (NotFoundException e) {
            throw new NotFoundException("Category with id " + id + " not found");
        }

    }

    // partially update category by id
    @PatchMapping("/{id}")
    public ResponseEntity<Void> partiallyUpdateCategory(
            @PathVariable int id,
            @Validated @RequestBody Map<String, Object> updates) {

        Optional<CategoryDTO> categoryOptional = categoryService.getCategoryById(id);

        if (categoryOptional.isPresent()) {
            CategoryDTO categoryDTO = categoryOptional.get();
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name":
                        categoryDTO.setName((String) value);
                        break;
                }
            });
            categoryService.updateCategory(id, categoryDTO);

            return ResponseEntity.ok().build();
        } else {
            throw new NotFoundException("Category with id " + id + " not found");
        }
    }

    //delete category by id
    @DeleteMapping("/{id}")
    public CategoryDTO deleteCategory(@PathVariable int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        categoryRepository.deleteById(id);
        return null;
    }


}

