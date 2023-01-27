package com.dpointtt.restapiexample.dto;

import java.util.List;

public record CategoryDTO(
        Long id,
        String categoryName,
        List<BookDTO> books
) {
}
