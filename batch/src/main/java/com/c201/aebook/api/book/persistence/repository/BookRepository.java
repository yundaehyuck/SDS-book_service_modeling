package com.c201.aebook.api.book.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.c201.aebook.api.book.persistence.entity.BookEntity;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByIsbn(String isbn);

    Optional<BookEntity> findById(long id);

}
