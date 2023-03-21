package com.yunbaek.blogsearchapplication.client.dto.kakao;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yunbaek.blogsearchapplication.client.mapper.KakaoLocalDateTimeDeserializer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Document {

	private String blogname;
	private String contents;
	@JsonDeserialize(using = KakaoLocalDateTimeDeserializer.class)
	private LocalDateTime datetime;

	private String thumbnail;
	private String title;
	private String url;

	public Document(String blogname, String contents, LocalDateTime datetime, String title, String url) {
		this.blogname = blogname;
		this.contents = contents;
		this.datetime = datetime;
		this.title = title;
		this.url = url;
	}
}
