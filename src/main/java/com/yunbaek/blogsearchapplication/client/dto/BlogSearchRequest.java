package com.yunbaek.blogsearchapplication.client.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class BlogSearchRequest {

	@NotBlank(message = "검색어를 입력해주세요.")
	private String query;

	private Sort sort;

	@Min(value = 1, message = "페이지는 1 이상의 숫자를 입력해주세요.")
	private Integer page;

	@Min(value = 1, message = "페이지당 검색 결과는 1 이상의 숫자를 입력해주세요.")
	private Integer size;

	public BlogSearchRequest(String query, Sort sort, Integer page, Integer size) {
		this.query = query;
		this.sort = sort == null ? Sort.ACCURACY : sort;
		this.page = page == null ? 1 : page;
		this.size = size == null ? 10 : size;
	}
}

