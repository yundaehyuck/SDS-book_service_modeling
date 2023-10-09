package com.c201.aebook.api.book.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class BookSearchResponseDTO {
    @Schema(description = "도서 제목", defaultValue = "철학이 필요한 시간 - 강신주의 인문학 카운슬링")
    private String title;

    @Schema(description = "지은이", defaultValue = "강신주 지음")
    private String author;

    @Schema(description = "출판사", defaultValue = "사계절출판사")
    private String publisher;

    @Schema(description = "출간일", defaultValue = "2011-02-15")
    private Date publishDate;

    @Schema(description = "13자리 ISBN", defaultValue = "9788958285342")
    private String isbn;

    @Schema(description = "도서 가격", defaultValue = "12460")
    private int price;

    @Schema(description = "알라딘 URL", defaultValue = "http://www.aladin.co.kr/shop/wproduct.aspx?ISBN=8958285346&partner=openAPI")
    private String aladinUrl;

    @Schema(description = "표지 이미지 주소", defaultValue = "주소")
    private String coverImageUrl;

    @Builder
    public BookSearchResponseDTO(String title, String author, String publisher, Date publishDate, String isbn, int price, String aladinUrl, String coverImageUrl) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.isbn = isbn;
        this.price = price;
        this.aladinUrl = aladinUrl;
        this.coverImageUrl = coverImageUrl;
    }
}
