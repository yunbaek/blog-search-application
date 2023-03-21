package com.yunbaek.blogsearchapplication.client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.client.dto.kakao.BlogSearchResult;
import com.yunbaek.blogsearchapplication.client.dto.naver.NaverBlogSearchResult;
import com.yunbaek.blogsearchapplication.client.factory.KakaoUriFactory;
import com.yunbaek.blogsearchapplication.client.factory.UriFactory;

@Service
@Order(2)
public class NaverBlogSearchClient extends AbstractBlogSearchClient {

	private final WebClient webClient;
	private final UriFactory uriFactory = new KakaoUriFactory();

	@Value("${application.external-api.naver.scheme}")
	private String scheme;

	@Value("${application.external-api.naver.host}")
	private String host;

	@Value("${application.external-api.naver.path}")
	private String path;

	public NaverBlogSearchClient(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public BlogSearchResult handleSearch(BlogSearchRequest request) {
		return webClient.get()
			.uri(builder -> getUri(request, builder))
			.header("X-Naver-Client-Id", "vKZOlRasLvIzKr2fJPqO")
			.header("X-Naver-Client-Secret", "6Oq3UjlVGH")
			.retrieve()
			.bodyToMono(NaverBlogSearchResult.class)
			.map(dto -> new BlogSearchResult(dto.getItems()))
			.block();
	}

	private URI getUri(BlogSearchRequest request, UriBuilder builder) {
		builder.host(host)
			.path(path)
			.scheme(scheme);
		return uriFactory.uri(request, builder);
	}
}
