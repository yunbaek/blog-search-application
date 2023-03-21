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

		builder.queryParam(SORT, request.getSort().getNaverSort());
		builder.queryParam(START, getStart(request));
		builder.queryParam(DISPLAY, request.getSize());
		return builder.build();
	}

	private static int getStart(BlogSearchRequest request) {
		return (request.getPage() - 1) * request.getSize() + 1;
	}
}
