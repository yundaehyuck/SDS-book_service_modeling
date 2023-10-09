package com.c201.aebook.api.story.presentation.validator;

import org.springframework.stereotype.Component;

import com.c201.aebook.api.common.CommonValidator;
import com.c201.aebook.api.story.presentation.dto.request.StoryRequestDTO;

@Component
public class StoryValidator extends CommonValidator {

	/**
	 * [홍예진] StoryRequestDTO NOT NULL 검증
	 * @param storyRequestDTO
	 */
	public void validateStoryRequestDTO(StoryRequestDTO storyRequestDTO) {
		checkStringType(storyRequestDTO.getTitle(), "동화 제목");
		checkStringType(storyRequestDTO.getContent(), "동화 내용");
	}
}
