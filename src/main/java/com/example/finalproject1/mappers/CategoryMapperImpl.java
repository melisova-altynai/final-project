package com.example.finalproject1.mappers;

import com.example.finalproject1.dto.CategoryDTO;
import com.example.finalproject1.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapperImpl implements CategoryMapper{
    @Override
    public CategoryDTO categorytoCategoryDTO(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO.CategoryDTOBuilder categoryDTO = CategoryDTO.builder();
        categoryDTO.name(category.getName());
        return categoryDTO.build();

    }

    @Override
    public Category categoryDTOtoCategory(CategoryDTO dto) {
        if (dto == null){
            return null;
        }
        Category.CategoryBuilder category = Category.builder();
        category.name(dto.getName());
        return category.build();

    }

}
