package com.c201.aebook.api.story.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.c201.aebook.api.common.BaseEntity;
import com.c201.aebook.api.user.persistence.entity.UserEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "story")
public class StoryEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", nullable = false, length = 500)
	private String title;

	@Column(name = "content", nullable = false, length = 1500)
	private String content;

	@Column(name = "img_url", nullable = false, length = 200)
	private String imgUrl;

	@Column(name = "voice_url", nullable = false, length = 200)
	private String voiceUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@Builder
	public StoryEntity(Long id, String title, String content, String imgUrl, String voiceUrl, UserEntity user) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.imgUrl = imgUrl;
		this.voiceUrl = voiceUrl;
		this.user = user;
	}

}
