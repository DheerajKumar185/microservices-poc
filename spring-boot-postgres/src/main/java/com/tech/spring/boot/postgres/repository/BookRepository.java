package com.tech.spring.boot.postgres.repository;

import com.tech.spring.boot.postgres.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
