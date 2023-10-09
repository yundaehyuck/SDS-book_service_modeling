package com.c201.aebook.api.review.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * [ 문희주 ] 서평 등록 Dto
 * 내용, 별점
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequestDTO {
	@Schema(description = "서평 내용", defaultValue = "장관이에요 절경이고요")
	private String content;
	@Schema(description = "별점", defaultValue = "5")
	private int score;
}
