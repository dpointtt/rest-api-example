package com.dpointtt.restapiexample.repository;

import com.dpointtt.restapiexample.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByCategory_CategoryName(String categoryName);
}
