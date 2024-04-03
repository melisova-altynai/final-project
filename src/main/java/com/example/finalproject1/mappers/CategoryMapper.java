package com.example.finalproject1.mappers;
import com.example.finalproject1.dto.CategoryDTO;
import com.example.finalproject1.entities.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {

    // Converts a Category entity to a CategoryDTO
    CategoryDTO categorytoCategoryDTO(Category category);
    // Converts a CategoryDTO to Category entity
    Category categoryDTOtoCategory(CategoryDTO categoryDTO);

}
