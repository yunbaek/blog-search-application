package com.yunbaek.blogsearchapplication.client.dto;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		BlogSearchRequest that = (BlogSearchRequest)o;

		if (!Objects.equals(query, that.query))
			return false;
		if (sort != that.sort)
			return false;
		if (!Objects.equals(page, that.page))
			return false;
		return Objects.equals(size, that.size);
	}

	@Override
	public int hashCode() {
		int result = query != null ? query.hashCode() : 0;
		result = 31 * result + (sort != null ? sort.hashCode() : 0);
		result = 31 * result + (page != null ? page.hashCode() : 0);
		result = 31 * result + (size != null ? size.hashCode() : 0);
		return result;
	}
}

