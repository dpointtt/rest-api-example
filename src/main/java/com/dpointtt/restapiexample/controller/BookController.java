package com.dpointtt.restapiexample.controller;

import com.dpointtt.restapiexample.dto.BookDTO;
import com.dpointtt.restapiexample.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBook(@PathVariable("id") Long id){
        return bookService.getBookById(id);
    }

    @PostMapping("/add")
    public BookDTO addBook(@RequestBody BookDTO bookDTO){
        return bookService.addNewBook(bookDTO);
    }

    @DeleteMapping("/remove/{id}")
    public void removeBook(@PathVariable Long id){
        bookService.deleteBookById(id);
    }

}
