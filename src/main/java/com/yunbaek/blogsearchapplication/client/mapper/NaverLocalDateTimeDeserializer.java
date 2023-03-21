package com.yunbaek.blogsearchapplication.client.mapper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class NaverLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

	@Override
	public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
		LocalDate localDate = LocalDate.parse(parser.getText(), FORMATTER);
		return localDate.atStartOfDay();
	}
}
