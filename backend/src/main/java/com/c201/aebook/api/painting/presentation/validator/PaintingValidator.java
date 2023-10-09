package com.c201.aebook.api.painting.presentation.validator;

import org.springframework.stereotype.Component;

import com.c201.aebook.api.common.CommonValidator;
import com.c201.aebook.api.painting.presentation.dto.request.PaintingRequestDTO;
import com.c201.aebook.api.painting.presentation.dto.request.PaintingTitleRequestDTO;

@Component
public class PaintingValidator extends CommonValidator {
	public void validatePaintingRequestDTO(PaintingRequestDTO paintingRequestDTO) {
		checkStringType(paintingRequestDTO.getTitle(), "그림 제목");
	}

	public void validatePaintingTitleRequestDTO(PaintingTitleRequestDTO paintingTitleRequestDTO) {
		checkStringType(paintingTitleRequestDTO.getTitle(), "그림 제목");
	}
}
