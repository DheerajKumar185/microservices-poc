package com.tech.spring.boot.postgres.service;

import com.tech.spring.boot.postgres.entity.Book;
import com.tech.spring.boot.postgres.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void createBook() {
        Book book = new Book(0, "A House for Mr. Biswas","V.S. Naipaul", 75.26);
        Book dbBook = bookRepository.save(book);
        assertNotNull(dbBook);
    }

    @Test
    void getBookById() {
        Optional<Book> book = bookRepository.findById(1);
        assertNotNull(book);
    }

    @Test
    void getAllBook() {
        List<Book> books = bookRepository.findAll();
        assertNotNull(books);
    }

    @Test
    void updateBook() {
        Book newBook = new Book(3, "A Million Mutinies Now","V.S. Naipaul", 75.26);
        Optional<Book> book = bookRepository.findById(3);
        if (book.isPresent()) {
            assertNotNull(bookRepository.save(newBook));
        }

    }

    @Test
    void deleteBook() {
        bookRepository.deleteById(7);
        assertTrue(true);
    }
}