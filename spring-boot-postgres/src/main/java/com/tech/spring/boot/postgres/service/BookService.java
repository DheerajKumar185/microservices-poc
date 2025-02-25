package com.tech.spring.boot.postgres.service;

import com.tech.spring.boot.postgres.constants.Constant;
import com.tech.spring.boot.postgres.entity.Book;
import com.tech.spring.boot.postgres.exception.ResourceNotFoundException;
import com.tech.spring.boot.postgres.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(Constant.RESOURCE_NOT_EXIST + id));
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public Book updateBook(int id, Book newBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setAuthor(newBook.getAuthor());
                    book.setPrice(newBook.getPrice());
                    return bookRepository.save(book);
                })
                .orElseThrow(()-> new ResourceNotFoundException(Constant.RESOURCE_NOT_EXIST + id));
    }

    public void deleteBook(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(Constant.RESOURCE_NOT_EXIST + id));
        bookRepository.delete(book);
    }
}
