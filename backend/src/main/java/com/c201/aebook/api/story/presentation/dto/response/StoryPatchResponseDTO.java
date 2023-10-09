package com.c201.aebook.api.story.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StoryPatchResponseDTO {

	@Schema(description = "동화 Id", defaultValue = "1")
	private Long storyId;
	@Schema(description = "동화 제목", defaultValue = "농부 삼촌과 신님")
	private String title;

}
