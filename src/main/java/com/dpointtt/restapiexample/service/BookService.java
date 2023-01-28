package com.dpointtt.restapiexample.service;

import com.dpointtt.restapiexample.dto.BookDTO;
import com.dpointtt.restapiexample.dto.BookUpdateRequest;
import com.dpointtt.restapiexample.entity.Book;
import com.dpointtt.restapiexample.repository.BookRepository;
import com.dpointtt.restapiexample.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public BookDTO addNewBook(BookUpdateRequest bookUpdateRequest){
        return Optional.of(bookRepository.save(
                new Book(
                        null,
                        bookUpdateRequest.title(),
                        bookUpdateRequest.author(),
                categoryRepository.findCategoryByCategoryName(bookUpdateRequest.category())
                        .orElseThrow(() -> new RuntimeException("Category not found")))))
                .map(bookDTOMapper)
                .orElseThrow(() -> new RuntimeException("Error creating object"));
    }

    @Transactional
    public BookDTO updateBookById(Long id, BookUpdateRequest bookUpdateRequest){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(bookUpdateRequest.title());
        book.setAuthor(bookUpdateRequest.author());
        book.setCategory(categoryRepository.findCategoryByCategoryName(bookUpdateRequest.category())
                .orElseThrow(() -> new RuntimeException("Category not found")));

        return Optional.of(bookRepository.save(book))
                .map(bookDTOMapper)
                .orElseThrow(() -> new RuntimeException("Error updating object"));
    }

    public List<BookDTO> getBooksByCategory(String categoryName) {
        return bookRepository
                .findBooksByCategory_CategoryName(categoryName)
                .stream()
                .map(bookDTOMapper)
                .collect(Collectors.toList());
    }
}
