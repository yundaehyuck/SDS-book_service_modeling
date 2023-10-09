package com.c201.aebook.api.common;

import com.c201.aebook.api.common.TokenDTO;
import com.c201.aebook.api.user.persistence.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginUserInfoDTO {

    private UserEntity user;
    private TokenDTO tokenDto;

}
