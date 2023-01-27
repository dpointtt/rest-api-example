package com.dpointtt.restapiexample.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
