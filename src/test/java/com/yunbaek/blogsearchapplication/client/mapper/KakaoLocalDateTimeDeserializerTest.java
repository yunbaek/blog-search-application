package com.yunbaek.blogsearchapplication.client.mapper;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

@DisplayName("카카오 날짜 Deserialize Test")
@ExtendWith(MockitoExtension.class)
class KakaoLocalDateTimeDeserializerTest {

	@Mock
	private JsonParser parser;

	@Mock
	private DeserializationContext context;

	@InjectMocks
	private KakaoLocalDateTimeDeserializer deserializer;

	@DisplayName("카카오 날짜 Deserialize 테스트 - 2023년 3월 21일 10시 30분 0초")
	@Test
	void kakaoLocalDateDeserializeTest() throws IOException {
		// given
		KakaoLocalDateTimeDeserializer deserializer = new KakaoLocalDateTimeDeserializer();
		String dateString = "2023-03-21T10:30:00.000+09:00";
		LocalDateTime expected = LocalDateTime.of(2023, 3, 21, 10, 30, 0);
		when(parser.getText()).thenReturn(dateString);

		// when
		LocalDateTime actual = deserializer.deserialize(parser, context);

		// then
		assertThat(actual).isEqualTo(expected);
	}

}
