package com.c201.aebook.api.notification.persistence.entity;

import com.c201.aebook.api.book.persistence.entity.BookEntity;
import com.c201.aebook.api.common.BaseEntity;
import com.c201.aebook.api.user.persistence.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "notification")
public class NotificationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="upper_limt")
    private int upperLimit;

    @Column(name = "notification_type", nullable = false, length = 10)
    private String notificationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @Builder
    public NotificationEntity(Long id, int upperLimit, String notificationType, UserEntity user, BookEntity book) {
        this.id = id;
        this.upperLimit = upperLimit;
        this.notificationType = notificationType;
        this.user = user;
        this.book = book;
    }

    public void updateNotificationEntity(int upperLimit, String notificationType) {
        this.upperLimit = upperLimit;
        this.notificationType = notificationType;
    }

}
