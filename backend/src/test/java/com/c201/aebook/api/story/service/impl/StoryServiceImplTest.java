package com.c201.aebook.api.story.service.impl;

import com.c201.aebook.api.story.persistence.entity.StoryEntity;
import com.c201.aebook.api.story.persistence.repository.StoryRepository;
import com.c201.aebook.api.story.presentation.dto.response.*;
import com.c201.aebook.api.user.persistence.entity.UserEntity;
import com.c201.aebook.api.user.persistence.repository.UserRepository;
import com.c201.aebook.api.vo.StoryDeleteSO;
import com.c201.aebook.api.vo.StoryPatchSO;
import com.c201.aebook.api.vo.StorySO;
import com.c201.aebook.converter.StoryConverter;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class StoryServiceImplTest {

	@Mock
	private StoryRepository storyRepository;

	@Mock
	private StoryConverter storyConverter;

	@InjectMocks
	private StoryServiceImpl subject;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	protected void setUp() throws Exception {
	}

	@DisplayName("testSaveStory: Happy Case")
	@Test
	public void testSaveStory() {
		// given
		StorySO storySO = StorySO.builder()
				.title("title")
				.content("content")
				.voiceUrl("voiceUrl")
				.imgUrl("imgUrl")
				.userId(1l)
				.build();
		UserEntity userEntity = UserEntity.builder().id(storySO.getUserId()).build();
		BDDMockito.given(userRepository.findById(storySO.getUserId()))
				.willReturn(Optional.of(userEntity));

		StoryEntity storyEntity = StoryEntity.builder()
				.title(storySO.getTitle())
				.content(storySO.getContent())
				.voiceUrl(storySO.getVoiceUrl())
				.imgUrl(storySO.getImgUrl())
				.user(userEntity)
				.build();

		StorySaveResponseDTO storySaveResponseDTO = StorySaveResponseDTO.builder()
				.title(storySO.getTitle())
				.build();

		BDDMockito.given(storyRepository.save(any()))
				.willReturn(storyEntity);
		BDDMockito.given(storyConverter.toStorySaveResponseDTO(storySO))
				.willReturn(storySaveResponseDTO);

		// when
		StorySaveResponseDTO ret = subject.saveStory(storySO);

		// then
		Assertions.assertAll("결과값 검증", () -> {
			Assertions.assertNotNull(ret);
			Assertions.assertEquals(ret.getTitle(), "title");
		});
		BDDMockito.then(userRepository)
				.should(times(1))
				.findById(storySO.getUserId());
		BDDMockito.then(storyRepository)
				.should(times(1))
				.save(any(StoryEntity.class));
	}

	@Test
	public void testGetStoryByUserId() {
		// given
		Long userId = 5l;
		Pageable pageable = PageRequest.of(0, 3, Sort.unsorted());

		List<StoryEntity> storyPageList = new ArrayList<>();
		UserEntity userEntity = UserEntity.builder().id(5l).nickname("nickname1").build();
		BDDMockito.given(userRepository.findById(userId))
				.willReturn(Optional.of(userEntity));

		StoryEntity storyEntity = StoryEntity.builder().user(userEntity).build();
		storyPageList.add(storyEntity);
		Page<StoryEntity> storyPage = new PageImpl<>(storyPageList, pageable, storyPageList.size());
		BDDMockito.given(storyRepository.findAll(pageable)).willReturn(storyPage);

		List<StoryListResponseDTO> storyList = new ArrayList<>();
		StoryListResponseDTO storyListResponseDTO = StoryListResponseDTO.builder()
				.nickname("nickname1")
				.build();
		storyList.add(storyListResponseDTO);

		BDDMockito.given(storyConverter.toStoryListResponseDTO(storyEntity))
				.willReturn(storyListResponseDTO);

		// when
		Page<StoryListResponseDTO> ret = subject.getStoryListByUserId(userId, pageable);

		// then
		Assertions.assertAll("결과값 검증", () -> {
			Assertions.assertNotNull(ret);
			Assertions.assertEquals(ret.getContent().get(0).getNickname(), "nickname1");
		});
		BDDMockito.then(storyRepository)
				.should(times(1))
				.findAll(pageable);
	}

	@Test
	public void testGetStoryList() {
		// given
		Pageable pageable = PageRequest.of(0, 3, Sort.unsorted());
		List<StoryEntity> storyPageList = new ArrayList<>();

		StoryEntity storyEntity = StoryEntity.builder().title("title").build();
		storyPageList.add(storyEntity);
		Page<StoryEntity> storyPage = new PageImpl<>(storyPageList, pageable, storyPageList.size());
		BDDMockito.given(storyRepository.findAll(pageable)).willReturn(storyPage);

		StoryListResponseDTO storyListResponseDTO = StoryListResponseDTO.builder()
				.title("title")
				.build();

		BDDMockito.given(storyConverter.toStoryListResponseDTO(storyEntity))
				.willReturn(storyListResponseDTO);

		// when
		Page<StoryListResponseDTO> ret = subject.getStoryList(pageable);

		// then
		Assertions.assertAll("결과값 검증", () -> {
			Assertions.assertNotNull(ret);
			Assertions.assertEquals(ret.getContent().get(0).getTitle(), "title");
		});
		BDDMockito.then(storyRepository)
				.should(times(1))
				.findAll(pageable);

	}

	@Test
	public void testGetStoryDetail() {
		// given
		Long storyId = 1l;

		StoryEntity storyEntity = StoryEntity.builder().id(storyId).build();
		BDDMockito.given(storyRepository.findById(storyId)).willReturn(Optional.of(storyEntity));

		StoryDetailResponseDTO storyDetailResponseDTO =  StoryDetailResponseDTO.builder()
				.storyId(1l)
				.title("title")
				.build();

		BDDMockito.given(storyConverter.toStoryDetailResponseDTO(storyEntity))
				.willReturn(storyDetailResponseDTO);

		// when
		StoryDetailResponseDTO ret = subject.getStoryDetail(storyId);

		// then
		Assertions.assertAll("결과값 검증", () -> {
			Assertions.assertNotNull(ret);
			Assertions.assertEquals(ret.getStoryId(), 1l);
			Assertions.assertEquals(ret.getTitle(), "title");
		});

		BDDMockito.then(storyRepository)
				.should(times(1))
				.findById(storyId);

	}

	@Test
	public void testUpdateStoryTitle() {
		// given
		StoryPatchSO storyPatchSO = StoryPatchSO.builder().storyId(1l).title("title").userId("5").build();

		UserEntity userEntity = UserEntity.builder().id(5l).nickname("nickname1").build();
		StoryEntity storyEntity = StoryEntity.builder()
				.id(storyPatchSO.getStoryId())
				.user(userEntity)
				.title(storyPatchSO.getTitle()).build();

		BDDMockito.given(storyRepository.findById(storyPatchSO.getStoryId())).willReturn(Optional.of(storyEntity));
		BDDMockito.given(userRepository.findById(5l)).willReturn(Optional.of(userEntity));

		StoryPatchResponseDTO storyPatchResponseDTO = StoryPatchResponseDTO.builder()
				.storyId(storyEntity.getId())
				.title(storyEntity.getTitle())
				.build();

		BDDMockito.given(storyConverter.toStoryPatchResponseDTO(storyPatchSO))
				.willReturn(storyPatchResponseDTO);

		// when
		StoryPatchResponseDTO ret = subject.updateStoryTitle(storyPatchSO);

		// then
		Assertions.assertAll("결과값 검증", () -> {
			Assertions.assertNotNull(ret);
			Assertions.assertEquals(ret.getStoryId(), 1l);
			Assertions.assertEquals(ret.getTitle(), "title");
		});

		BDDMockito.then(storyRepository)
				.should(times(1))
				.findById(storyPatchSO.getStoryId());
		BDDMockito.then(userRepository)
				.should(times(1))
				.findById(Long.parseLong(storyPatchSO.getUserId()));
		BDDMockito.then(storyRepository)
				.should(times(1))
				.save(any(StoryEntity.class));

	}

	@Test
	public void testDeleteStory() {
		// given
		StoryDeleteSO storyDeleteSO = StoryDeleteSO.builder().storyId(1l).userId(5l).build();

		UserEntity userEntity = UserEntity.builder().id(5l).nickname("nickname1").build();
		StoryEntity storyEntity = StoryEntity.builder()
				.id(storyDeleteSO.getStoryId())
				.title("title")
				.user(userEntity).build();

		BDDMockito.given(storyRepository.findById(storyDeleteSO.getStoryId())).willReturn(Optional.of(storyEntity));
		BDDMockito.given(userRepository.findById(5l)).willReturn(Optional.of(userEntity));

		StoryDeleteResponseDTO storyDeleteResponseDTO = StoryDeleteResponseDTO.builder()
				.storyId(storyEntity.getId())
				.title(storyEntity.getTitle())
				.build();

		BDDMockito.given(storyConverter.toStoryDeleteResponseDTO(storyEntity))
				.willReturn(storyDeleteResponseDTO);

		// when
		StoryDeleteResponseDTO ret = subject.deleteStory(storyDeleteSO);

		// then
		Assertions.assertAll("결과값 검증", () -> {
			Assertions.assertNotNull(ret);
			Assertions.assertEquals(ret.getStoryId(), 1l);
			Assertions.assertEquals(ret.getTitle(), "title");
		});

		BDDMockito.then(storyRepository)
				.should(times(1))
				.findById(storyDeleteSO.getStoryId());
		BDDMockito.then(userRepository)
				.should(times(1))
				.findById(storyDeleteSO.getUserId());
		BDDMockito.then(storyRepository)
				.should(times(1))
				.deleteById(storyDeleteSO.getStoryId());
	}

}
