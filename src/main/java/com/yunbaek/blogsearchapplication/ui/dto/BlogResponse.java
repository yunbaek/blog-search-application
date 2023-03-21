package com.yunbaek.blogsearchapplication.ui.dto;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BlogResponse {

	private String blogName;
	private String contents;
	private String url;
	private String thumbnailUrl;
	private String title;
	private LocalDateTime createTime;
}
