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
public class ReviewMyResponseDTO {
	@Schema(description = "서평 ID", defaultValue = "1")
	private Long id;

	@Schema(description = "별점", defaultValue = "5")
	private int score;

	@Schema(description = "서평 내용", defaultValue = "절경이고요 장관이네요")
	private String content;

	@Schema(description = "isbn", defaultValue = "제 3인류 1")
	private String isbn;

	@Schema(description = "도서 제목", defaultValue = "")
	private String title;

	@Schema(description = "서평 작성 시간", defaultValue = "2023-04-14 10:30:15")
	private LocalDateTime createdAt;

	@Schema(description = "서평 수정 시간", defaultValue = "2023-04-15 10:3-:15")
	private LocalDateTime updatedAt;

	@Builder
	public ReviewMyResponseDTO(Long id, int score, String content, String isbn, String title,
		LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.score = score;
		this.content = content;
		this.isbn = isbn;
		this.title = title;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
