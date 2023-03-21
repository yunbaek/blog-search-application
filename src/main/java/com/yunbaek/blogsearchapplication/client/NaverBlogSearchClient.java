package com.yunbaek.blogsearchapplication.client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.client.dto.naver.NaverBlogSearchResult;
import com.yunbaek.blogsearchapplication.client.factory.NaverUriFactory;
import com.yunbaek.blogsearchapplication.client.factory.UriFactory;
import com.yunbaek.blogsearchapplication.client.mapper.ResponseFromNaverMapper;
import com.yunbaek.blogsearchapplication.ui.dto.BlogSearchResponse;

@Service
@Order(2)
public class NaverBlogSearchClient extends AbstractBlogSearchClient {

	private static final String NAVER_CLIENT_ID = "X-Naver-Client-Id";
	private static final String NAVER_CLIENT_SECRET = "X-Naver-Client-Secret";
	private final WebClient webClient;
	private final ResponseFromNaverMapper mapper;
	private final UriFactory uriFactory = new NaverUriFactory();

	@Value("${application.external-api.naver.scheme}")
	private String scheme;

	@Value("${application.external-api.naver.host}")
	private String host;

	@Value("${application.external-api.naver.path}")
	private String path;

	@Value("${application.external-api.naver.client-id}")
	private String clientId;

	@Value("${application.external-api.naver.client-secret}")
	private String clientSecret;

	public NaverBlogSearchClient(WebClient webClient, ResponseFromNaverMapper mapper) {
		this.webClient = webClient;
		this.mapper = mapper;
	}

	@Override
	public BlogSearchResponse handleSearch(BlogSearchRequest request) {
		NaverBlogSearchResult searchResult = webClient.get()
			.uri(builder -> getUri(request, builder))
			.header(NAVER_CLIENT_ID, clientId)
			.header(NAVER_CLIENT_SECRET, clientSecret)
			.retrieve()
			.bodyToMono(NaverBlogSearchResult.class)
			.block();

		return mapper.from(searchResult, request);
	}

	private URI getUri(BlogSearchRequest request, UriBuilder builder) {
		builder.host(host)
			.path(path)
			.scheme(scheme);
		return uriFactory.uri(request, builder);
	}
}
