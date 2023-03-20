package com.yunbaek.blogsearchapplication.client.factory;

import java.net.URI;

import org.springframework.web.util.UriBuilder;

import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;

public class NaverUriFactory implements UriFactory {
	private static final String QUERY = "query";
	private static final String SORT = "sort";
	private static final String START = "start";
	private static final String DISPLAY = "display";

	@Override
	public URI uri(BlogSearchRequest request, UriBuilder builder) {

		if (request.getSort() != null) {
			builder.queryParam(SORT, request.getSort().getNaverSort());
		}
		if (request.getPage() != null) {
			builder.queryParam(START, request.getPage());
		}
		if (request.getSize() != null) {
			builder.queryParam(DISPLAY, request.getSize());
		}
		return builder
			.queryParam(QUERY, request.getQuery())
			.build();

	}
}
