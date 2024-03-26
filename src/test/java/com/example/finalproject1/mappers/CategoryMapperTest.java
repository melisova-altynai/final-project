package com.example.finalproject1.mappers;

import com.example.finalproject1.dto.CategoryDTO;
import com.example.finalproject1.entities.Category;
import com.example.finalproject1.mappers.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryMapperTest {

    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    void categoryToCategoryDTOTest() {

        Category category = Category.builder()
                .name("music")
                .build();

        CategoryDTO categoryDTO = mapper.categorytoCategoryDTO(category);

        assertEquals("music", categoryDTO.getName());
    }

    @Test
    void categoryDTOtoCategoryTest() {
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .name("sports")
                .build();


        Category category = mapper.categoryDTOtoCategory(categoryDTO);


        assertEquals("sports", category.getName());

    }
}
