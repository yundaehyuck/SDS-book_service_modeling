package com.c201.aebook.api.painting.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaintingTitleRequestDTO {
	@Schema(description = "그림 제목", defaultValue = "하늘을 나는 공룡")
	private String title;
}
