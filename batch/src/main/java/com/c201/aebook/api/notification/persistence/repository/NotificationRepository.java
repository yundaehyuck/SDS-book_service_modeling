package com.c201.aebook.api.notification.persistence.repository;

import com.c201.aebook.api.notification.persistence.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    /* 알림톡 테스트에 사용*/
    @Query("SELECT n FROM NotificationEntity n JOIN FETCH n.user u WHERE n.book.id = :bookId")
    List<NotificationEntity> findByBookId(@Param("bookId") Long bookId);

    @Query("SELECT n FROM NotificationEntity n JOIN FETCH n.user u " +
            "WHERE n.book.id = :bookId AND n.notificationType = :notificationType")
    List<NotificationEntity> findByBookIdAndNotificationType(@Param("bookId") Long bookId, @Param("notificationType") String notificationType);

}
