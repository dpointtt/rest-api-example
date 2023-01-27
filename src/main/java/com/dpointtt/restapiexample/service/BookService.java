package com.dpointtt.restapiexample.service;

import com.dpointtt.restapiexample.dto.BookDTO;
import com.dpointtt.restapiexample.entity.Book;
import com.dpointtt.restapiexample.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookDTOMapper bookDTOMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookDTOMapper bookDTOMapper){
        this.bookRepository = bookRepository;
        this.bookDTOMapper = bookDTOMapper;
    }

    public List<BookDTO> getAllBooks(){
        return bookRepository
                .findAll()
                .stream()
                .map(bookDTOMapper)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id){
        return bookRepository
                .findById(id)
                .map(bookDTOMapper)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}
