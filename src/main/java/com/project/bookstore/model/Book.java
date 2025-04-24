package com.project.bookstore.model;

import com.project.bookstore.model.eum.BookType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "book_title")
    private String title;

    @Column(nullable = false, name = "book_price")
    private double basePrice;

    @Enumerated(EnumType.STRING)
    private BookType type;
}
