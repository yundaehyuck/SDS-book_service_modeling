package com.c201.aebook.api.review.presentation.validator;

import org.springframework.stereotype.Component;

import com.c201.aebook.api.common.CommonValidator;
import com.c201.aebook.api.review.presentation.dto.request.ReviewRequestDTO;

/**
 * [문희주] DTO NOT NULL 검증
 */

@Component
public class ReviewValidator extends CommonValidator {

	/**
	 * [문희주] ReviewRequestDTO NOT NULL 검증
	 * @param reviewRequestDTO
	 */
	public void validateReviewRequestDTO(ReviewRequestDTO reviewRequestDTO) {
		checkStringType(reviewRequestDTO.getContent(), "서평 내용");
		checkIntType(reviewRequestDTO.getScore(), "별점");
	}
}
