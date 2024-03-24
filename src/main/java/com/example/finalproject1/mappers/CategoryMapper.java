package com.example.finalproject1.mappers;
import com.example.finalproject1.dto.CategoryDTO;
import com.example.finalproject1.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper {
    CategoryDTO categorytoCategoryDTO(Category category);
    Category categoryDTOtoCategory(CategoryDTO categoryDTO);

}
