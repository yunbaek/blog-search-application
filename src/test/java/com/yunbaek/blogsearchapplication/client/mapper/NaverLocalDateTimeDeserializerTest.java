package com.yunbaek.blogsearchapplication.client.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

@DisplayName("네이버 날짜 Deserialize Test")
@ExtendWith(MockitoExtension.class)
class NaverLocalDateTimeDeserializerTest {

	@Mock
	private JsonParser parser;

	@Mock
	private DeserializationContext context;

	@InjectMocks
	private NaverLocalDateTimeDeserializer deserializer;

	@DisplayName("네이버 날짜 Deserialize 테스트 - 2023년 3월 21일 0시 0분 0초")
	@Test
	void naverLocalDateDeserializeTest() throws Exception {
		// given
		String dateString = "20230321";
		NaverLocalDateTimeDeserializer deserializer = new NaverLocalDateTimeDeserializer();
		LocalDateTime expected = LocalDateTime.of(2023, 3, 21, 0, 0, 0);
		when(parser.getText()).thenReturn(dateString);

		// when
		LocalDateTime actual = deserializer.deserialize(parser, context);

		// then
		assertThat(actual).isEqualTo(expected);
	}

}
