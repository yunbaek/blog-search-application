package com.yunbaek.blogsearchapplication.ui.dto;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogRequest {

	private String blogName;
	private String contents;
	private String url;
	private String title;
	private LocalDateTime createTime;
}
