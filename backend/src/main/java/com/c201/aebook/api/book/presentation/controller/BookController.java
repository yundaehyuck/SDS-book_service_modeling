package com.c201.aebook.api.book.presentation.controller;

import java.util.List;

import com.c201.aebook.auth.CustomUserDetails;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.c201.aebook.api.book.presentation.dto.response.BookResponseDTO;
import com.c201.aebook.api.book.presentation.dto.response.BookSearchResponseDTO;
import com.c201.aebook.api.book.presentation.dto.response.BookSimpleResponseDTO;
import com.c201.aebook.api.book.service.BookService;
import com.c201.aebook.api.common.BaseResponse;
import com.c201.aebook.api.common.constants.ApplicationConstants;
import com.c201.aebook.utils.RegexValidator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "도서 Controller")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
	private final BookService bookService;
	private final RegexValidator regexValidator;

	@Operation(summary = "ISBN으로 도서 검색", description = "해당 ISBN의 도서 상세 정보를 반환합니다.")
	@GetMapping("/{isbn}")
	public BaseResponse<?> searchBookDetail(
			@AuthenticationPrincipal @Nullable CustomUserDetails customUserDetails,
			@PathVariable(name = "isbn") String isbn
	) {
		// ISBN 검증
		regexValidator.validateIsbn(isbn);
		BookResponseDTO book = bookService.searchBookDetail(isbn, customUserDetails);
		return new BaseResponse<>(book, HttpStatus.OK.value(), ApplicationConstants.SUCCESS);
	}

	@Operation(summary = "키워드로 책 제목 자동완성", description = "키워드를 입력하면 책 제목을 자동 완성합니다.")
	@GetMapping("/autocomplete")
	public BaseResponse<?> getAutocompleteTitle(@RequestParam(name = "keyword") String keyword) {
		List<String> titleList = bookService.getAutocompleteTitle(keyword);
		return new BaseResponse<>(titleList, HttpStatus.OK.value(), ApplicationConstants.SUCCESS);
	}

	@Operation(summary = "도서 통합 검색", description = "검색 키워드를 포함하는 도서 리스트를 반환합니다.")
	@GetMapping
	public BaseResponse<?> searchBookList(@RequestParam("keyword") String keyword,
		@RequestParam(name = "searchTargets", defaultValue = "TITLE,AUTHOR,PUBLISHER") String[] searchTargets,
		@PageableDefault(size = 10) Pageable pageable) {
		Page<BookSearchResponseDTO> bookSearchList = bookService.searchBookList(keyword, searchTargets, pageable);
		return new BaseResponse<>(bookSearchList, HttpStatus.OK.value(), ApplicationConstants.SUCCESS);
	}

	@Operation(summary = "최신 도서 검색", description = "최근 업데이트된 도서 리스트를 반환합니다.")
	@GetMapping("/new")
	public BaseResponse<?> getNewBookList() {
		List<BookSimpleResponseDTO> newBookList = bookService.getNewBookList();
		return new BaseResponse<>(newBookList, HttpStatus.OK.value(), ApplicationConstants.SUCCESS);
	}

}
