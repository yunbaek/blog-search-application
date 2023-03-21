package com.yunbaek.blogsearchapplication.exception.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

public class ApiError {
	private int code;
	private String message;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;

	private ApiError(int code, String message) {
		this.code = code;
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}

	public static ApiError of(int code, String message) {
		return new ApiError(code, message);
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "ApiError{" +
			"code='" + code + '\'' +
			", message='" + message + '\'' +
			", timestamp=" + timestamp +
			'}';
	}
}
