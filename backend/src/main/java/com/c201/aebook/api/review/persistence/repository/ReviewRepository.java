package com.c201.aebook.api.review.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.c201.aebook.api.review.persistence.entity.ReviewEntity;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
	@Query("SELECT re FROM ReviewEntity re LEFT JOIN FETCH re.user LEFT JOIN FETCH re.book WHERE re.id = :reviewId")
	public Optional<ReviewEntity> findById(Long reviewId);

	public Optional<ReviewEntity> findByUserIdAndBookId(Long userId, Long bookId);

	@Query(value = "SELECT re FROM ReviewEntity re LEFT JOIN FETCH re.user WHERE re.book.id = :bookId",
		countQuery = "SELECT COUNT(re) FROM ReviewEntity re WHERE re.book.id = :bookId")
	public Page<ReviewEntity> findByBookId(Long bookId, Pageable pageable);

	public Optional<ReviewEntity> findByIdAndUserId(Long reviewId, Long userId);

	@Query(value = "SELECT re FROM ReviewEntity re LEFT JOIN FETCH re.user LEFT JOIN FETCH  re.book WHERE re.user.id = :userId",
		countQuery = "SELECT count(re) FROM ReviewEntity re WHERE re.user.id = :userId")
	public Page<ReviewEntity> findByUserId(Long userId, Pageable pageable);

	@Query(value = "SELECT re FROM ReviewEntity re LEFT JOIN FETCH re.user LEFT JOIN FETCH re.book",
		countQuery = "SELECT count(re) FROM ReviewEntity re")
	public List<ReviewEntity> findTop12ByOrderByIdDesc(Pageable pageable);

}
