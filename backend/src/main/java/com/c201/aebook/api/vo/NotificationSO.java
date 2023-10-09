package com.c201.aebook.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationSO {

    private String isbn;
    private int upperLimit;
    private String notificationType;

}
