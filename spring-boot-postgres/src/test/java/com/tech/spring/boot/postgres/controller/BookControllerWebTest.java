package com.tech.spring.boot.postgres.controller;

import com.tech.spring.boot.postgres.entity.Book;
import com.tech.spring.boot.postgres.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerWebTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    private Book book;
    List<Book> bookList = null;

    @BeforeEach
    void setUp() {
        book = new Book(1, "Making India Awesome","Chetan Bhagat", 39.99);
        bookList = Arrays.asList(
                new Book(2, "Naked Triangle","Balwant Gargi", 61.95),
                new Book(3, "A Million Mutinies Now","V.S. Naipaul", 75.26));
    }

    @Test
    void testCreateBook() throws Exception {
        when(bookService.createBook(ArgumentMatchers.any(Book.class))).thenReturn(book);
        mockMvc.perform(post("/books/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Making India Awesome\",\"author\":\"Chetan Bhagat\",\"price\":39.99}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Making India Awesome"))
                .andExpect(jsonPath("$.author").value("Chetan Bhagat"))
                .andExpect(jsonPath("$.price").value(39.99));
    }

    @Test
    void testGetBookById() throws Exception {
        when(bookService.getBookById(1)).thenReturn(book);
        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Making India Awesome"))
                .andExpect(jsonPath("$.author").value("Chetan Bhagat"))
                .andExpect(jsonPath("$.price").value(39.99));
    }

    @Test
    void testGetAllBook() throws Exception {
        when(bookService.getAllBook()).thenReturn(bookList);
        mockMvc.perform(get("/books/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].title").value("Naked Triangle"))
                .andExpect(jsonPath("$[0].author").value("Balwant Gargi"))
                .andExpect(jsonPath("$[0].price").value(61.95));
    }

    @Test
    void testUpdateBook() throws Exception {
        int id = 1;
        when(bookService.updateBook(ArgumentMatchers.anyInt(), ArgumentMatchers.any(Book.class))).thenReturn(book);
        mockMvc.perform(put("/books/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"title\":\"Making India Awesome\",\"author\":\"Chetan Bhagat\",\"price\":39.99}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Making India Awesome"))
                .andExpect(jsonPath("$.author").value("Chetan Bhagat"))
                .andExpect(jsonPath("$.price").value(39.99));
    }

    @Test
    void testDeleteBook() throws Exception {
        int id = 1;
        doNothing().when(bookService).deleteBook(id);
        mockMvc.perform(delete("/books/{id}", id))
                .andExpect(status().isNoContent())
                .andExpect(content().string("Deleted id: " + id));
        verify(bookService, times(1)).deleteBook(id);

    }
}
