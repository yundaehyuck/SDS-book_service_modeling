package com.c201.aebook.api.auth.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponseDTO {

    @Schema(description = "사용자 Id", defaultValue = "1")
    private final Long userId;

    @Schema(description = "사용자 닉네임", defaultValue = "아이북")
    private final String nickname;

    @Schema(description = "프로필 사진 링크", defaultValue = "http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg")
    private final String profileUrl;
}
