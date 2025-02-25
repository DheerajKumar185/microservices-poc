package com.tech.spring.boot.postgres.controller;

import com.tech.spring.boot.postgres.constants.Constant;
import com.tech.spring.boot.postgres.entity.Book;
import com.tech.spring.boot.postgres.exception.ResourceNotFoundException;
import com.tech.spring.boot.postgres.repository.BookRepository;
import com.tech.spring.boot.postgres.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookControllerTest {
    @Autowired
    private BookService bookService;

    @MockitoBean
    private BookRepository bookRepository;

    private Book book;
    List<Book> bookList = null;
    final int id = 4;

    @BeforeEach
    public void setUp() {
        book = new Book(1, "Making India Awesome","Chetan Bhagat", 39.99);
        bookList = Arrays.asList(
                new Book(2, "Naked Triangle","Balwant Gargi", 61.95),
                new Book(3, "A Million Mutinies Now","V.S. Naipaul", 75.26));
    }

    @Test
    void createBook() {
        when(bookRepository.save(book)).thenReturn(book);
        assertEquals(book, bookService.createBook(book));
    }

    @Test
    void getBookById() {
        when(bookRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(book));
        assertEquals(book, bookService.getBookById(1));
    }

    @Test
    void getBookById_NotFound() {
        when(bookRepository.findById(id)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, ()->bookService.getBookById(id));
        assertEquals(Constant.RESOURCE_NOT_EXIST + id, exception.getMessage());
    }

    @Test
    void getAllBook() {
        when(bookRepository.findAll()).thenReturn(bookList);
        assertEquals(2, bookService.getAllBook().size());
    }

    @Test
    void updateBook() {
        when(bookRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        assertEquals(book, bookService.updateBook(1, book));
    }

    @Test
    void updateBook_IdNotFound() {
        when(bookRepository.findById(id)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, ()-> bookService.updateBook(id, book));
        assertEquals(Constant.RESOURCE_NOT_EXIST + id, exception.getMessage());
    }

    @Test
    void deleteBook() {
        when(bookRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.of(book));
        bookService.deleteBook(1);
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    void deleteBook_IdNotFound() {
        when(bookRepository.findById(id)).thenReturn(Optional.empty());
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, ()-> bookService.deleteBook(id));
        assertEquals(Constant.RESOURCE_NOT_EXIST + id, exception.getMessage());
    }
}