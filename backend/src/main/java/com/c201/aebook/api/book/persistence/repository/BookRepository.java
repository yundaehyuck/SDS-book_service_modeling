package com.c201.aebook.api.book.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.c201.aebook.api.book.persistence.entity.BookEntity;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
	public Optional<BookEntity> findByIsbn(String isbn);

	public List<BookEntity> findTop5ByTitleContaining(String keyword);

	public List<BookEntity> findTop16ByOrderByUpdatedAtDesc();

	@Query("select b.title from BookEntity b where b.id = :bookId")
	public String findTitleById(Long bookId);

	/* 알림톡 테스트에 사용 */
	@Query("select b.price from BookEntity b where b.id = :bookId")
	public int findPriceById(Long bookId);
}
