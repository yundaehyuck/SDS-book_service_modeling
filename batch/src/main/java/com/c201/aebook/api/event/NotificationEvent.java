package com.c201.aebook.api.event;

import org.springframework.context.ApplicationEvent;

import com.c201.aebook.api.book.persistence.entity.BookEntity;

import java.awt.print.Book;

/*
* 알림을 위한 이벤트 클래스
* */
public class NotificationEvent extends ApplicationEvent {

	private BookEntity bookEntity;
	private BookEntity beforeEntity;

	public NotificationEvent(Object source, BookEntity bookEntity, BookEntity beforeEntity) {
		super(source);
		this.bookEntity = bookEntity;
		this.beforeEntity = beforeEntity;
	}

	public BookEntity getBookEntity() {
		return bookEntity;
	}

}
