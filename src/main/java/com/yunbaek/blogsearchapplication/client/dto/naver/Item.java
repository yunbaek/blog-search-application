package com.yunbaek.blogsearchapplication.client.dto.naver;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

	private String title;
	private String link;
	private String description;
	private String bloggername;
	private String bloggerlink;
	private LocalDateTime postdate;
}
