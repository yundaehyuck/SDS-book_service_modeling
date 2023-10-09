package com.c201.aebook.api.notification.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class NotificationBookDetailResponseDTO {

    @Schema(description = "알림 ID", defaultValue = "1")
    private Long id;

    @Schema(description = "사용자 상한가", defaultValue = "1000")
    private int upperLimit;

    /**
     * S는 사용자가 가격을 지정한 경우(selected),
     * D는 사용자가 가격을 지정하지 않은 경우(default)
     */
    @Schema(description = "알림 타입", defaultValue = "S")
    private String notificationType;


    @Schema(description = "알림 신청 시간", defaultValue = "2023-04-14 10:30:15")
    private LocalDateTime createdAt;

    @Schema(description = "알림 수정 시간", defaultValue = "2023-04-17 10:30:15")
    private LocalDateTime updatedAt;

    @Schema(description = "도서 제목", defaultValue = "철학이 필요한 시간 - 강신주의 인문학 카운슬링")
    private String title;

    @Schema(description = "지은이", defaultValue = "강신주 지음")
    private String author;

    @Schema(description = "출판사", defaultValue = "사계절출판사")
    private String publisher;

    @Schema(description = "13자리 ISBN", defaultValue = "9788958285342")
    private String isbn;

    @Schema(description = "도서 가격", defaultValue = "12460")
    private int price;

    @Schema(description = "표지 이미지 주소", defaultValue = "주소")
    private String coverImageUrl;

    @Schema(description = "알라딘 URL", defaultValue = "http://www.aladin.co.kr/shop/wproduct.aspx?ISBN=8958285346&partner=openAPI")
    private String aladinUrl;

    @Builder
    public NotificationBookDetailResponseDTO(Long id, int upperLimit, String notificationType, LocalDateTime createdAt, LocalDateTime updatedAt, String title, String author, String publisher, String isbn, int price, String coverImageUrl, String aladinUrl) {
        this.id = id;
        this.upperLimit = upperLimit;
        this.notificationType = notificationType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.price = price;
        this.coverImageUrl = coverImageUrl;
        this.aladinUrl = aladinUrl;
    }
}
