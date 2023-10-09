package com.c201.aebook.converter;

import org.mapstruct.Mapper;

import com.c201.aebook.api.book.persistence.entity.BookEntity;
import com.c201.aebook.api.book.presentation.dto.response.BookResponseDTO;
import com.c201.aebook.api.book.presentation.dto.response.BookSearchResponseDTO;
import com.c201.aebook.api.book.presentation.dto.response.BookSimpleResponseDTO;

@Mapper(componentModel = "spring")
public interface BookConverter {
	BookSimpleResponseDTO toBookSimpleResponseDTO(BookEntity bookEntity);

	BookSearchResponseDTO toBookSearchResponseDTO(BookEntity bookEntity);

	BookResponseDTO toBookResponseDTO(BookEntity bookEntity);
}
