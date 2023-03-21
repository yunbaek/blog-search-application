package com.yunbaek.blogsearchapplication.client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.client.dto.kakao.BlogSearchResult;
import com.yunbaek.blogsearchapplication.client.factory.KakaoUriFactory;
import com.yunbaek.blogsearchapplication.client.factory.UriFactory;

@Service
@Order(1)
public class KakaoBlogSearchClient extends AbstractBlogSearchClient {

	private final WebClient webClient;
	private final UriFactory uriFactory = new KakaoUriFactory();

	@Value("${application.external-api.kakao.scheme}")
	private String scheme;

	@Value("${application.external-api.kakao.host}")
	private String host;

	@Value("${application.external-api.kakao.path}")
	private String path;

	public KakaoBlogSearchClient(WebClient webClient) {
		this.webClient = webClient;
	}

	@Override
	public BlogSearchResult handleSearch(BlogSearchRequest request) {
		return webClient.get()
			.uri(builder -> getUri(request, builder))
			.header("Authorization", "KakaoAK 7a06dbbb7157ea25c1b3a61102c9ce3f")
			.retrieve()
			.onStatus(status -> status.is4xxClientError()
					|| status.is5xxServerError() || status.is2xxSuccessful()
				, clientResponse ->
					clientResponse.bodyToMono(String.class)
						.map(IllegalArgumentException::new))
			.bodyToMono(BlogSearchResult.class)
			.block();
	}

	private URI getUri(BlogSearchRequest request, UriBuilder builder) {
		builder.host(host)
			.path(path)
			.scheme(scheme);
		return uriFactory.uri(request, builder);
	}
}
