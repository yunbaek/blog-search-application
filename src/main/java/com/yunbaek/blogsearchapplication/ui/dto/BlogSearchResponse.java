package com.yunbaek.blogsearchapplication.ui.dto;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BlogSearchResponse {

	private List<BlogResponse> blogs;
	private Integer currentPage;
	private Integer size;
	private Long total;
}
