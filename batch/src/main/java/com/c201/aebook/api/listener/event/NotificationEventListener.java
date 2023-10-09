package com.c201.aebook.api.listener.event;

import com.c201.aebook.api.notification.persistence.entity.NotificationEntity;
import com.c201.aebook.api.notification.persistence.repository.NotificationRepository;
import com.c201.aebook.api.notification.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.c201.aebook.api.book.persistence.entity.BookEntity;
import com.c201.aebook.api.event.NotificationEvent;

import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Component
public class NotificationEventListener implements ApplicationListener<NotificationEvent> {

	private final NotificationService notificationService;

	@Override
	public void onApplicationEvent(NotificationEvent event) {
		BookEntity book = event.getBookEntity();//최저가가 갱신된 BookEntity

		// TODO: 여기에 진행을 하는 걸까요...?

		// 도서 정보
		String bookTitle = book.getTitle();
		int bookPrice = book.getPrice();
		Long bookId = book.getId();

		// 1-1. notificationRepository에서 최저가 알림(D)을 신청한 사용자와 알림 정보를 bookId와 notificationType으로 가져오기
		List<NotificationEntity> lowestPriceUserList = notificationService.getNotificationUserInfoByBookIdAndNotificationType(bookId, "D");

		// 1-2. notificationRepository에서 사용자 지정 최저가 알림(S)을 신청한 사용자와 알림 정보를 bookId와 notificationType으로 가져오기
		List<NotificationEntity> customizeUserList = notificationService.getNotificationUserInfoByBookIdAndNotificationType(bookId, "S");

		// 2. token 정보 얻어오기
		String token = null;
		try {
			token = notificationService.createToken();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		// 3. 알림 타입에 따라 해당 탬플릿으로 알림톡 전송
		// 3-1. 여기는 최저가 알림(D) 타입 탬플릿
		ResponseEntity<String> LowestPriceResponse = notificationService.LowestPriceTalk(token, lowestPriceUserList, bookTitle);
		// 3-2. 여기는 사용자 지정 최저가 알림(S) 타입 탬플릿
		ResponseEntity<String> customizeLowestPrice = notificationService.CustomizeLowestPriceTalk(token, customizeUserList, bookTitle, bookPrice);
		if(customizeLowestPrice == null) {
			log.info("전송대상 없음");
		}

	}

	//TODO : 선영님이 여기에 알림 만들어야 함
	
}
