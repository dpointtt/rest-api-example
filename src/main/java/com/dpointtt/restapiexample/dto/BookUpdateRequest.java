package com.dpointtt.restapiexample.dto;

public record BookUpdateRequest(
        String title,
        String author,
        String category
) {
}
