package com.c201.aebook.api.book.presentation.dto.response;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookResponseDTO {
	@Schema(description = "도서 제목", defaultValue = "철학이 필요한 시간 - 강신주의 인문학 카운슬링")
	private String title;

	@Schema(description = "도서 소개", defaultValue = "\"나는 왜 이러고 살지?\"의 주인공들을 위한 인문 공감 에세이. 지금은 자기 위로와 자기 최면이 아닌 아파도 당당하게 상처를 마주할 수 있게 하는 인문학이 필요하다. 이 책은 니체, 스피노자, 원효, 데리다 등 철학자들의 인문 고전을 통해 고민과 불안에 갇혀 있는 이들에게 솔직하게 삶에 직면하고 상처를 치유할 수 있는 방법을 제시한다.")
	private String description;

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

	@Schema(description = "알림 신청 여부", defaultValue = "true")
	private boolean notification;

	@Schema(description = "알림 ID", defaultValue = "2")
	private Long notificationId;

	@Schema(description = "리뷰 전체 점수")
	private int scoreSum;

	@Schema(description = "리뷰 개수")
	private int reviewCount;

	@Builder
	public BookResponseDTO(String title, String description, String author, String publisher, Date publishDate,
		String isbn, int price, String aladinUrl, String coverImageUrl, int scoreSum, int reviewCount) {
		this.title = title;
		this.description = description;
		this.author = author;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.isbn = isbn;
		this.price = price;
		this.aladinUrl = aladinUrl;
		this.coverImageUrl = coverImageUrl;
		this.reviewCount = reviewCount;
		this.scoreSum = scoreSum;
	}
}
