package com.dpointtt.restapiexample.service;

import com.dpointtt.restapiexample.dto.BookDTO;
import com.dpointtt.restapiexample.entity.Book;
import com.dpointtt.restapiexample.repository.BookRepository;
import com.dpointtt.restapiexample.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookDTOMapper bookDTOMapper;

    @Autowired
    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository, BookDTOMapper bookDTOMapper){
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
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

    public void deleteBookById(Long id){
        bookRepository.delete(bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found")));
    }

    public BookDTO addNewBook(BookDTO bookDTO){
        Optional<Book> book = Optional.of(new Book(bookDTO.id(), bookDTO.title(), bookDTO.author(),
                categoryRepository.findCategoryByCategoryName(bookDTO.category())
                        .orElseThrow(() -> new RuntimeException("Category not found"))));

        bookRepository.save(book.orElseThrow());

        return book.map(bookDTOMapper).orElseThrow(() -> new RuntimeException("Error"));
    }

}
