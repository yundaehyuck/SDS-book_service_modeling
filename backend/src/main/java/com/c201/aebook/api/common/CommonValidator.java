package com.c201.aebook.api.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CommonValidator {
	public void checkLongType(Long value, String name) {
		if (value == null || value <= 0) {
			throw new IllegalArgumentException(name + "은/는 필수 입력값이며 양수 값만 가능합니다.");
		}
	}

	public void checkIntType(int value, String name) {
		if (value <= 0) {
			throw new IllegalArgumentException(name + "은/는 필수 입력값이며 양수 값만 가능합니다.");
		}
	}

	public void checkStringType(String value, String name) {
		if (StringUtils.isBlank(value)) {
			throw new IllegalArgumentException(name + "은/는 필수 입력값입니다.");
		}
	}
}
