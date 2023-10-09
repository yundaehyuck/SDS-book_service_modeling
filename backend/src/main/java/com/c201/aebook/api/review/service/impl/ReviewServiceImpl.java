package com.c201.aebook.api.review.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.c201.aebook.api.book.persistence.entity.BookEntity;
import com.c201.aebook.api.book.persistence.repository.BookRepository;
import com.c201.aebook.api.review.persistence.entity.ReviewEntity;
import com.c201.aebook.api.review.persistence.repository.ReviewRepository;
import com.c201.aebook.api.review.presentation.dto.response.ReviewBookResponseDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewMainResponseDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewMyResponseDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewResponseDTO;
import com.c201.aebook.api.review.service.ReviewService;
import com.c201.aebook.api.user.persistence.entity.UserEntity;
import com.c201.aebook.api.user.persistence.repository.UserRepository;
import com.c201.aebook.api.vo.ReviewSO;
import com.c201.aebook.converter.ReviewConverter;
import com.c201.aebook.utils.exception.CustomException;
import com.c201.aebook.utils.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	private final BookRepository bookRepository;
	private final ReviewConverter reviewConverter;

	@Override
	@Transactional
	public ReviewResponseDTO saveReview(String userId, String isbn, ReviewSO reviewSO) {
		// 1. isbn 유효성 검증
		BookEntity bookEntity = bookRepository.findByIsbn(isbn)
			.orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));

		// 2. userId 유효성 검증
		UserEntity userEntity = userRepository.findById(Long.valueOf(userId))
			.orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

		// 3. 해당 책에 서평을 작성한 적 없는지 검증
		ReviewEntity reviewEntity = reviewRepository.findByUserIdAndBookId(userEntity.getId(), bookEntity.getId())
			.orElse(null);

		if (reviewEntity != null) {
			throw new CustomException(ErrorCode.DUPLICATED_REVIEW);
		}

		// 4. 서평 저장
		reviewEntity = reviewRepository.save(reviewConverter.toReviewEntity(reviewSO, userEntity, bookEntity));

		// 5. 도서 별점 정보 갱신
		bookEntity.updateScoreInfo(reviewSO.getScore(), 1);

		ReviewResponseDTO result = reviewConverter.toReviewResponseDTO(reviewEntity);
		return result;
	}

	@Override
	public Page<ReviewBookResponseDTO> getBookReviewList(String isbn, Pageable pageable) {
		// 1. isbn 유효성 검증
		BookEntity bookEntity = bookRepository.findByIsbn(isbn)
			.orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));

		// 2. Review List
		Page<ReviewEntity> reviews = reviewRepository.findByBookId(bookEntity.getId(), pageable);

		Page<ReviewBookResponseDTO> result = reviews.map(review -> reviewConverter.toReviewBookResponseDTO(review));
		return result;
	}

	@Override
	public Page<ReviewMyResponseDTO> getMyReviewList(String userId, Pageable pageable) {
		Page<ReviewEntity> reviews = reviewRepository.findByUserId(Long.valueOf(userId), pageable);

		Page<ReviewMyResponseDTO> result = reviews.map(review -> reviewConverter.toReviewMyResponseDTO(review));
		return result;
	}

	@Override
	public ReviewResponseDTO getMyReview(String userId, String isbn) {
		// 1. isbn으로 도서 찾기
		BookEntity bookEntity = bookRepository.findByIsbn(isbn)
			.orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));

		// 2. 리뷰 검색
		ReviewEntity reviewEntity = reviewRepository.findByUserIdAndBookId(Long.valueOf(userId), bookEntity.getId())
			.orElse(null);

		ReviewResponseDTO result = reviewConverter.toReviewResponseDTO(reviewEntity);
		return result;
	}

	@Override
	@Transactional
	public ReviewResponseDTO modifyReview(Long reviewId, String userId, ReviewSO reviewSO) {
		// 1. reviewId 유효성, 작성자 아이디 일치 검증
		ReviewEntity reviewEntity = reviewRepository.findByIdAndUserId(reviewId, Long.valueOf(userId))
			.orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));

		// 2. 해당 도서의 별점 정보 변경
		BookEntity bookEntity = bookRepository.findById(reviewEntity.getBook().getId())
			.orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
		bookEntity.updateScoreInfo(-reviewEntity.getScore(), 0);
		bookEntity.updateScoreInfo(reviewSO.getScore(), 0);

		// 3. 서평 수정
		reviewEntity.updateReviewEntity(reviewSO.getContent(), reviewSO.getScore());
		reviewRepository.save(reviewEntity);

		ReviewResponseDTO result = reviewConverter.toReviewResponseDTO(reviewEntity);
		return result;
	}

	@Override
	@Transactional
	public void deleteReview(Long reviewId, String userId) {
		// 1. reviewId 유효성, 작성자 아이디 일치 검증
		ReviewEntity reviewEntity = reviewRepository.findByIdAndUserId(reviewId, Long.valueOf(userId))
			.orElseThrow(() -> new CustomException(ErrorCode.REVIEW_NOT_FOUND));

		// 2. 해당 도서의 별점 정보 변경
		BookEntity bookEntity = bookRepository.findById(reviewEntity.getBook().getId())
			.orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
		bookEntity.updateScoreInfo(-reviewEntity.getScore(), -1);

		// 3. 서평 삭제
		reviewRepository.delete(reviewEntity);
	}

	@Override
	public List<ReviewMainResponseDTO> getLatestReviewList(Pageable pageable) {
		List<ReviewEntity> reviews = reviewRepository.findTop12ByOrderByIdDesc(pageable);

		List<ReviewMainResponseDTO> result = reviews.stream()
			.map(review -> reviewConverter.toReviewMainResponseDTO(review))
			.collect(Collectors.toList());

		return result;
	}
}
