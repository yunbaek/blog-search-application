package com.yunbaek.blogsearchapplication.client.dto.naver;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NaverBlogSearchResult {

	private Integer total;
	private Integer start;
	private Integer display;

	private List<Item> items;

}
