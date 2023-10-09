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
public class ReviewBookResponseDTO {
	@Schema(description = "서평 ID", defaultValue = "1")
	private Long id;

	@Schema(description = "서평 작성자 ID", defaultValue = "1")
	private Long reviewerId;

	@Schema(description = "서평 작성자 닉네임", defaultValue = "아응앵")
	private String reviewerNickname;

	@Schema(description = "서평 작성자 프로필 사진", defaultValue = "https://mblogthumb-phinf.pstatic.net/MjAyMTAxMTVfODYg/MDAxNjEwNzA3OTI5OTI4.ox4Vx8aW7HgllLtYNyrC2Uo9w3g76FMh7yhcGXEXftgg.nlp930B9S1ZurCoJulgcgOHgmBvgenCQtxAXCzPZFOUg.JPEG.41minit/1610707726106.jpg?type=w800")
	private String reviewerImg;

	@Schema(description = "별점", defaultValue = "5")
	private int score;

	@Schema(description = "서평 내용", defaultValue = "절경이고요 장관이네요")
	private String content;

	@Schema(description = "서평 작성 시간", defaultValue = "2023-04-14 10:30:15")
	private LocalDateTime createdAt;

	@Schema(description = "서평 수정 시간", defaultValue = "2023-04-15 10:3-:15")
	private LocalDateTime updatedAt;

	@Builder
	public ReviewBookResponseDTO(Long id, Long reviewerId, String reviewerNickname, String reviewerImg, int score,
		String content,
		LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.reviewerId = reviewerId;
		this.reviewerNickname = reviewerNickname;
		this.reviewerImg = reviewerImg;
		this.score = score;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

}
