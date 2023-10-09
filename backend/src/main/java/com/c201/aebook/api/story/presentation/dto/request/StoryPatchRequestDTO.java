package com.c201.aebook.api.story.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoryPatchRequestDTO {
	@Schema(description = "동화 제목", defaultValue = "농부 삼촌과 신님")
	private String title;
}
