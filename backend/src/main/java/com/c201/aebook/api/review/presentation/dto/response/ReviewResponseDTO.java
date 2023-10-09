package com.c201.aebook.api.review.presentation.dto.response;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDTO {
	@Schema(description = "서평 ID", defaultValue = "1")
	private Long id;

	@Schema(description = "별점", defaultValue = "5")
	private int score;

	@Schema(description = "서평 내용", defaultValue = "장관이에요 절경이고요")
	private String content;

	@Schema(description = "서평 작성자 ID", defaultValue = "1")
	private Long reviewerId;

	@Schema(description = "서평을 작성한 도서 ID", defaultValue = "1")
	private Long bookId;

	@Schema(description = "서평 등록 시간", defaultValue = "2023-04-14 10:30:15")
	private LocalDateTime createdAt;

	@Schema(description = "서평 수정 시간", defaultValue = "2023-04-15 10:30:15")
	private LocalDateTime updatedAt;

	@Builder
	public ReviewResponseDTO(Long id, int score, String content, Long reviewerId, Long bookId, LocalDateTime createdAt,
		LocalDateTime updatedAt) {
		this.id = id;
		this.score = score;
		this.content = content;
		this.reviewerId = reviewerId;
		this.bookId = bookId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
