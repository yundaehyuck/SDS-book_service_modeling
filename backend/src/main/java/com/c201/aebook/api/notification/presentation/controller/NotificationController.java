package com.c201.aebook.api.notification.presentation.controller;

import com.c201.aebook.api.common.BaseResponse;
import com.c201.aebook.api.common.constants.ApplicationConstants;
import com.c201.aebook.api.notification.presentation.dto.request.NotificationRequestDTO;
import com.c201.aebook.api.notification.presentation.dto.request.NotificationUpdateRequestDTO;
import com.c201.aebook.api.notification.presentation.dto.response.NotificationBookDetailResponseDTO;
import com.c201.aebook.api.notification.presentation.dto.response.NotificationBookListResponseDTO;
import com.c201.aebook.api.notification.presentation.dto.response.NotificationResponseDTO;
import com.c201.aebook.api.notification.presentation.dto.response.NotificationUpdateResponseDTO;
import com.c201.aebook.api.notification.presentation.vaildator.NotificationValidator;
import com.c201.aebook.api.notification.service.impl.NotificationServiceImpl;
import com.c201.aebook.api.vo.NotificationPatchSO;
import com.c201.aebook.api.vo.NotificationSO;
import com.c201.aebook.auth.CustomUserDetails;
import com.c201.aebook.converter.NotificationConverter;
import com.c201.aebook.utils.RegexValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name="알림톡")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationValidator notificationValidator;
    private final RegexValidator regexValidator;
    private final NotificationConverter notificationConverter;
    private final NotificationServiceImpl notificationService;

    @Operation(summary = "알림 신청", description = "사용자가 하나의 책에 알림 신청을 합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping()
    public BaseResponse<?> saveNotification(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody NotificationRequestDTO notificationRequestDTO

    ) {
        // 1. DTO NOT NULL 검증
        notificationValidator.validateNotificationRequestDTO(notificationRequestDTO);

        // 2. isbn 검증
        regexValidator.validateIsbn(notificationRequestDTO.getIsbn());

        // 3. 알림 등록
        NotificationSO notificationSO = notificationConverter.toNotificationSO(notificationRequestDTO);
        NotificationResponseDTO notificationResponseDTO =
                notificationService.saveNotification(customUserDetails.getUsername(), notificationSO);

        return new BaseResponse<>(notificationResponseDTO, HttpStatus.OK.value(), "알림 신청 성공");
    }

    @Operation(summary = "알림 신청한 책 목록", description = "사용자가 알림 신청한 책의 목록을 출력합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping()
    public BaseResponse<?> getNotificationBookList(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PageableDefault(size = 6, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        // 1. 사용자가 알림 신청한 책 목록 가져오기
        Page<NotificationBookListResponseDTO> notificationBookList = notificationService.getMyNotificationBookList(customUserDetails.getUsername(), pageable);
        return new BaseResponse<>(notificationBookList, HttpStatus.OK.value(), ApplicationConstants.SUCCESS);
    }

    @Operation(summary = "알림 상세조회", description = "사용자가 신청한 알림에 대한 상세조회를 합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{notificationId}")
    public BaseResponse<?> getNotificationBookDetail(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable(name = "notificationId") Long notificationId
    ) {
        // 1. 사용자가 신청한 알림에 대한 상세조회(알림 자체 정보와 책 정보를 결합)
        NotificationBookDetailResponseDTO notificationBookDetail = notificationService.getMyNotificationBookDetail(notificationId);
        return new BaseResponse<>(notificationBookDetail, HttpStatus.OK.value(), ApplicationConstants.SUCCESS);
    }

    @Operation(summary = "알림 수정", description = "사용자가 신청한 알림을 수정합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/{notificationId}")
    public BaseResponse<?> updateNotification(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable(name = "notificationId") Long notificationId,
            @RequestBody NotificationUpdateRequestDTO notificationUpdateRequestDTO
    ) {
        // 1. DTO NOT NULL 검증
        notificationValidator.validateNotificationUpdateRequestDTO(notificationUpdateRequestDTO);

        // 2. 알림 수정
        NotificationPatchSO notificationPatchSO = notificationConverter.toNotificationPatchSO(notificationUpdateRequestDTO);
        NotificationUpdateResponseDTO notificationUpdateResponseDTO =
                notificationService.updateNotification(customUserDetails.getUsername(), notificationId, notificationPatchSO);

        return new BaseResponse<>(notificationUpdateResponseDTO, HttpStatus.OK.value(), ApplicationConstants.SUCCESS);
    }

    @Operation(summary = "알림 삭제", description = "사용자가 신청한 알림을 삭제합니다.")
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{notificationId}")
    public BaseResponse<?> deleteNotification(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable(name = "notificationId") Long notificationId
    ) {
        // 알림 삭제
        notificationService.deleteNotification(customUserDetails.getUsername(), notificationId);
        return new BaseResponse<>(null, HttpStatus.OK.value(), ApplicationConstants.SUCCESS);
    }

}
