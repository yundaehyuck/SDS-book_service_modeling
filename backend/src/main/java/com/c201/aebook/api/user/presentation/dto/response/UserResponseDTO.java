package com.c201.aebook.api.user.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {

    @Schema(description = "변경한 닉네임", defaultValue = "aebook")
    private String nickname;

    @Schema(description = "프로필 이미지", defaultValue = "http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg")
    private String profileUrl;

    @Builder
    public UserResponseDTO(String nickname, String profileUrl) {
        this.nickname = nickname;
        this.profileUrl = profileUrl;
    }
}
