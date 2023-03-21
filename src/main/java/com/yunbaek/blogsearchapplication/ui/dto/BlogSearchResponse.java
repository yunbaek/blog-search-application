package com.yunbaek.blogsearchapplication.ui.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogSearchResponse {

	private List<BlogRequest> blogs;
	private int currentPage;
	private int size;
	private long total;
}
