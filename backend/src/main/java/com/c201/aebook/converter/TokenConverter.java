package com.c201.aebook.converter;

import com.c201.aebook.api.vo.TokenSO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenConverter {

    TokenSO toTokenSO(String accessToken, String refreshToken);

}
