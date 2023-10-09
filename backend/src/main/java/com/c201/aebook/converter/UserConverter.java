package com.c201.aebook.converter;

import com.c201.aebook.api.user.presentation.dto.response.UserResponseDTO;
import com.c201.aebook.api.vo.UserSO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter {

    UserSO toUserSO(String nickname, String profileUrl);

    UserResponseDTO toUserResponse(String nickname, String profileUrl);

}
