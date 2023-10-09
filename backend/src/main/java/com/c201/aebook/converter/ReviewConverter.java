package com.c201.aebook.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.c201.aebook.api.book.persistence.entity.BookEntity;
import com.c201.aebook.api.review.persistence.entity.ReviewEntity;
import com.c201.aebook.api.review.presentation.dto.request.ReviewRequestDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewBookResponseDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewMainResponseDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewMyResponseDTO;
import com.c201.aebook.api.review.presentation.dto.response.ReviewResponseDTO;
import com.c201.aebook.api.user.persistence.entity.UserEntity;
import com.c201.aebook.api.vo.ReviewSO;

@Mapper(componentModel = "spring")
public interface ReviewConverter {

	ReviewSO toReviewSO(ReviewRequestDTO reviewRequestDTO);

	@Mappings({
		@Mapping(source = "user.id", target = "reviewerId"),
		@Mapping(source = "book.id", target = "bookId")
	})
	ReviewResponseDTO toReviewResponseDTO(ReviewEntity reviewEntity);

	@Mappings({
		@Mapping(source = "user.id", target = "reviewerId"),
		@Mapping(source = "user.nickname", target = "reviewerNickname"),
		@Mapping(source = "user.profileUrl", target = "reviewerImg")
	})
	ReviewBookResponseDTO toReviewBookResponseDTO(ReviewEntity reviewEntity);

	@Mappings({
		@Mapping(source = "book.isbn", target = "isbn"),
		@Mapping(source = "book.title", target = "title")
	})
	ReviewMyResponseDTO toReviewMyResponseDTO(ReviewEntity reviewEntity);

	@Mappings({
		@Mapping(source = "user.nickname", target = "reviewerNickname"),
		@Mapping(source = "book.isbn", target = "isbn")
	})
	ReviewMainResponseDTO toReviewMainResponseDTO(ReviewEntity reviewEntity);

	@Mappings({
		@Mapping(source = "userEntity", target = "user"),
		@Mapping(source = "bookEntity", target = "book"),
		@Mapping(target = "id", ignore = true) // id 속성은 매핑하지 않음
	})
	ReviewEntity toReviewEntity(ReviewSO reviewSO, UserEntity userEntity, BookEntity bookEntity);
}
