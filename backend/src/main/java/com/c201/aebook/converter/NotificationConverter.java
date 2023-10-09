package com.c201.aebook.converter;

import com.c201.aebook.api.notification.persistence.entity.NotificationEntity;
import com.c201.aebook.api.notification.presentation.dto.request.NotificationRequestDTO;
import com.c201.aebook.api.notification.presentation.dto.request.NotificationUpdateRequestDTO;
import com.c201.aebook.api.notification.presentation.dto.response.NotificationBookDetailResponseDTO;
import com.c201.aebook.api.notification.presentation.dto.response.NotificationBookListResponseDTO;
import com.c201.aebook.api.notification.presentation.dto.response.NotificationResponseDTO;
import com.c201.aebook.api.notification.presentation.dto.response.NotificationUpdateResponseDTO;
import com.c201.aebook.api.vo.NotificationPatchSO;
import com.c201.aebook.api.vo.NotificationSO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationConverter {

    NotificationSO toNotificationSO(NotificationRequestDTO notificationRequestDTO);
    NotificationBookListResponseDTO toNotificationBookListResponseDTO(NotificationEntity notificationEntity);

    NotificationPatchSO toNotificationPatchSO(NotificationUpdateRequestDTO notificationUpdateRequestDTO);
    NotificationUpdateResponseDTO toNotificationUpdateResponseDTO(Integer upperLimit, String notificationType);

    NotificationResponseDTO toNotificationResponseDTO(NotificationEntity notificationEntity);

}
