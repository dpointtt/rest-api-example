package com.dpointtt.restapiexample.service;

import com.dpointtt.restapiexample.dto.CategoryDTO;
import com.dpointtt.restapiexample.entity.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {
    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getCategoryName()
        );
    }
}
