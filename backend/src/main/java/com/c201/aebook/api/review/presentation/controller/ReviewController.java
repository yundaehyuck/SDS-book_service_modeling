package com.c201.aebook.api.review.presentation.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c201.aebook.api.common.BaseResponse;
import com.c201.aebook.api.common.constants.ApplicationConstants;
import com.c201.aebook.api.review.presentation.dto.request.ReviewRequestDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewBookResponseDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewMainResponseDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewMyResponseDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewResponseDTO;
import com.c201.aebook.api.review.presentation.validator.ReviewValidator;
import com.c201.aebook.api.review.service.impl.ReviewServiceImpl;
import com.c201.aebook.api.vo.ReviewSO;
import com.c201.aebook.auth.CustomUserDetails;
import com.c201.aebook.converter.ReviewConverter;
import com.c201.aebook.utils.RegexValidator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * [문희주] 서평 관련 컨트롤러
 */
@Tag(name = "서평 Controller")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

	private final ReviewValidator reviewValidator;
	private final RegexValidator regexValidator;
	private final ReviewServiceImpl reviewService;
	private final ReviewConverter reviewConverter;
	//	private final TokenUtils tokenUtils; // TODO: 선영아 "해줘"

	@Operation(summary = "서평 등록", description = "새 서평을 등록합니다.")
	@PostMapping(
		path = "/{isbn}",
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public BaseResponse<?> saveReview(
		@PathVariable(name = "isbn") String isbn,
		@RequestBody ReviewRequestDTO reviewRequestDTO,
		@AuthenticationPrincipal CustomUserDetails customUserDetails
	) {
		// DTO NOT NULL 검증
		reviewValidator.validateReviewRequestDTO(reviewRequestDTO);

		// isbn 검증
		regexValidator.validateIsbn(isbn);

		ReviewSO reviewSO = reviewConverter.toReviewSO(reviewRequestDTO);
		// 서평 등록
		ReviewResponseDTO reviewResponseDTO = reviewService.saveReview(customUserDetails.getUsername(), isbn,
			reviewSO);

		return new BaseResponse<>(reviewResponseDTO, 200, "서평 작성 완료");
	}

	@Operation(summary = "특정 도서의 서평 리스트", description = "도서 상세 페이지에서 보여줄 서평 리스트입니다.")
	@GetMapping
		(
			path = "/{isbn}"
		)
	public BaseResponse<?> getBookReviewList(
		@PathVariable(name = "isbn") String isbn,
		@PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
	) {
		// isbn 검증
		regexValidator.validateIsbn(isbn);

		// 해당 도서 서평 리스트 찾기
		Page<ReviewBookResponseDTO> reviews = reviewService.getBookReviewList(isbn, pageable);

		return new BaseResponse<>(reviews, 200, "해당 책의 서평 리스트가 도착했읍니다 ^_^b");
	}

	@Operation(summary = "사용자 서평 조회", description = "현재 로그인한 사용자가 작성한 서평 리스트입니다.")
	@GetMapping
	public BaseResponse<?> getMyReviewList(
		@AuthenticationPrincipal CustomUserDetails customUserDetails,
		@PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
	) {
		Page<ReviewMyResponseDTO> reviews = reviewService.getMyReviewList(customUserDetails.getUsername(), pageable);

		return new BaseResponse<>(reviews, 200, ApplicationConstants.SUCCESS);
	}

	@Operation(summary = "특정 도서 내 서평 조회", description = "선택한 도서에 내가 등록한 서평의 정보를 얻습니다.")
	@GetMapping(
		path = "/{isbn}/mine"
	)
	public BaseResponse<?> getMyReview(
		@PathVariable(name = "isbn") String isbn,
		@AuthenticationPrincipal CustomUserDetails customUserDetails
	) {
		// isbn 검증
		regexValidator.validateIsbn(isbn);

		ReviewResponseDTO review = reviewService.getMyReview(customUserDetails.getUsername(), isbn);

		return new BaseResponse<>(review, 200, ApplicationConstants.SUCCESS);
	}

	@Operation(summary = "특정 서평 수정", description = "선택한 서평의 내용을 수정합니다.")
	@PatchMapping(
		path = "/{reviewId}",
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public BaseResponse<?> modifyReview(
		@PathVariable(name = "reviewId") Long reviewId,
		@RequestBody ReviewRequestDTO reviewRequestDTO,
		@AuthenticationPrincipal CustomUserDetails customUserDetails
	) {
		// DTO NOT NULL 검증
		reviewValidator.validateReviewRequestDTO(reviewRequestDTO);

		ReviewSO reviewSO = reviewConverter.toReviewSO(reviewRequestDTO);

		// 서평 수정
		ReviewResponseDTO reviewResponseDTO = reviewService.modifyReview(reviewId, customUserDetails.getUsername(),
			reviewSO);

		return new BaseResponse<>(reviewResponseDTO, 200, ApplicationConstants.SUCCESS);
	}

	@Operation(summary = "특정 서평 삭제", description = "선택한 서평을 삭제합니다.")
	@DeleteMapping(
		path = "/{reviewId}"
	)
	public BaseResponse<?> deleteReview(
		@PathVariable(name = "reviewId") Long reviewId,
		@AuthenticationPrincipal CustomUserDetails customUserDetails
	) {
		// 서평 삭제
		reviewService.deleteReview(reviewId, customUserDetails.getUsername());

		return new BaseResponse<>(null, 200, ApplicationConstants.SUCCESS);
	}

	@Operation(summary = "최신 서평 리스트", description = "메인페이지에서 보여줄 최신 서평 12개 리스트입니다")
	@GetMapping(
		path = "/latest"
	)
	public BaseResponse<?> getLatestReviewList(
		@PageableDefault(size = 12, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
	) {
		List<ReviewMainResponseDTO> reviews = reviewService.getLatestReviewList(pageable);

		return new BaseResponse<>(reviews, 200, ApplicationConstants.SUCCESS);
	}
}
