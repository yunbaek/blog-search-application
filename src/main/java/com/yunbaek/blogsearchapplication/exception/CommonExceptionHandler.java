package com.yunbaek.blogsearchapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.yunbaek.blogsearchapplication.exception.dto.ApiError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {
	@ExceptionHandler({IllegalArgumentException.class, WebClientResponseException.class, WebClientRequestException.class})
	public ResponseEntity<ApiError> handleDuplicateAndInvalidDataException(RuntimeException e) {
		log.error("Bad Request -- message : " + e.getMessage());
		ApiError error = ApiError.of(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return ResponseEntity.badRequest().body(error);
	}

}
