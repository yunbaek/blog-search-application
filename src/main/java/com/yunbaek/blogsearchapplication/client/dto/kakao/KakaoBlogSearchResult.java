package com.yunbaek.blogsearchapplication.client.dto.kakao;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoBlogSearchResult {
	private Meta meta;
	private List<Document> documents;

}
