package com.c201.aebook.api.review.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.c201.aebook.api.review.presentation.dto.response.ReviewBookResponseDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewMainResponseDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewMyResponseDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewResponseDTO;
import com.c201.aebook.api.vo.ReviewSO;

public interface ReviewService {
	// 1. saveReview : 서평 등록
	public ReviewResponseDTO saveReview(String userId, String isbn, ReviewSO reviewSO);

	// 2. getBookReviewList : 특정 책에 달린 서평 리스트
	public Page<ReviewBookResponseDTO> getBookReviewList(String isbn, Pageable pageable);

	// 3. getMyReviewList : 현재 로그인한 유저가 등록한 서평 리스트
	public Page<ReviewMyResponseDTO> getMyReviewList(String userId, Pageable pageable);

	// 4. getReview : 특정 서평 조회
	public ReviewResponseDTO getMyReview(String userId, String isbn);

	// 5. modifyReview : 특정 서평 수정
	public ReviewResponseDTO modifyReview(Long reviewId, String userId, ReviewSO reviewSO);

	// 6. deleteReview : 특정 서평 삭제
	public void deleteReview(Long reviewId, String userId);

	// 7. getNewReviewList : 최신 서평 12개
	public List<ReviewMainResponseDTO> getLatestReviewList(Pageable pageable);
}
