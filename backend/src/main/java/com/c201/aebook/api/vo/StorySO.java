package com.c201.aebook.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StorySO {
	private String title;
	private String content;
	private String imgUrl;
	private String voiceUrl;
	private Long userId;
}
