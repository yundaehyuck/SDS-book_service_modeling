package com.c201.aebook.api.vo;

import com.c201.aebook.api.painting.persistence.entity.PaintingType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaintingSO {
	private String title;
	private String fileUrl;
	private PaintingType type;
	private Long userId;
}
