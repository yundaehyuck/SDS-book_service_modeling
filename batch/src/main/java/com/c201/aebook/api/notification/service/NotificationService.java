package com.c201.aebook.api.notification.service;

import com.c201.aebook.api.notification.persistence.entity.NotificationEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface NotificationService {

    public String createToken() throws JsonProcessingException, ParseException;

    public ResponseEntity<String> LowestPriceTalk(String token, List<NotificationEntity> userList, String bookTitle);

    public ResponseEntity<String> CustomizeLowestPriceTalk(String token, List<NotificationEntity> userList, String bookTitle, int price);

    public List<NotificationEntity> getNotificationUserInfoByBookId(Long bookId, String notificationType);
    public List<NotificationEntity> getNotificationUserInfoByBookIdAndNotificationType(Long bookId, String notificationType);

    public boolean containsKeyStartingWith(MultiValueMap<String, Object> map, String keyPrefix);

}
