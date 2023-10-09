package com.c201.aebook.api.story.presentation.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StorySaveResponseDTO {
	@Schema(description = "동화 제목", defaultValue = "농부 삼촌과 신님")
	private String title;
	@Schema(description = "동화 내용", defaultValue =
		"한 농부 삼촌이 있었어요. 농부 삼촌은 큰 밭을 가지고 있었지만, 늘 부족한 것이 있었어요. 그래서 그는 신께 기도를 했어요. 어느 날, 농부 삼촌은 신의 가르침을 받았어요.\n"
			+ "\n"
			+ "신은 농부 삼촌에게 이야기했답니다. \"네가 원하는 것은 모든 것을 갖는 것이 아니라, 네가 가진 것을 충분히 활용하는 것이다.\" 농부 삼촌은 신의 말씀을 따라 열심히 일하기 시작했어요. 그 결과, 수확이 많아졌답니다.\n"
			+ "\n"
			+ "농부 삼촌은 신의 가르침을 받아 자신이 가진 것을 충분히 활용하는 방법을 배웠어요. 이제 그는 늘 감사하며 살아가며, 노력이 결실을 맺는 것을 보며 기쁨을 느꼈답니다.")
	private String content;

	@Schema(description = "동화 그림")
	private String imgUrl;
	
	@Schema(description = "동화 목소리")
	private String voiceUrl;
}
