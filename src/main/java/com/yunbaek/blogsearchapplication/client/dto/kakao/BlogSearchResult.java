package com.yunbaek.blogsearchapplication.client.dto.kakao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.yunbaek.blogsearchapplication.client.dto.naver.Item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogSearchResult {
	private Meta meta;
	private List<Document> documents;

	public BlogSearchResult(List<Item> items) {
		documents = items.stream()
			.map(item -> new Document(item.getTitle(), item.getDescription(), LocalDateTime.now(), item.getTitle(),
				item.getLink()))
			.collect(Collectors.toList());
	}
}
