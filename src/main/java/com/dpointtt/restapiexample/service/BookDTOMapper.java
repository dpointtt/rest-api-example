package com.dpointtt.restapiexample.service;

import com.dpointtt.restapiexample.dto.BookDTO;
import com.dpointtt.restapiexample.entity.Book;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BookDTOMapper implements Function<Book, BookDTO> {
    @Override
    public BookDTO apply(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory().getCategoryName()
        );
    }
}
