package com.yunbaek.blogsearchapplication.client.factory;

import java.net.URI;

import org.springframework.web.util.UriBuilder;

import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;

public class KakaoUriFactory implements UriFactory {

	private static final String QUERY = "query";
	private static final String SORT = "sort";
	private static final String PAGE = "page";
	private static final String SIZE = "size";

	@Override
	public URI uri(BlogSearchRequest request, UriBuilder builder) {
		builder.queryParam(QUERY, request.getQuery());
		builder.queryParam(SORT, request.getSort().getKakaoSort());
		builder.queryParam(PAGE, request.getPage());
		builder.queryParam(SIZE, request.getSize());
		return builder.build();
	}
}
