package com.yunbaek.blogsearchapplication.client.factory;

import static org.assertj.core.api.Assertions.*;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.client.dto.Sort;

@DisplayName("카카오 URI Factory 테스트")
class KakaoUriFactoryTest {

	private UriComponentsBuilder uriComponentsBuilder;

	@BeforeEach
	void setUp() {
		uriComponentsBuilder = UriComponentsBuilder.fromUriString("https://example.com");
	}

	@DisplayName("모든 검색 조건이 있으면 정상적으로 URI를 생성한다.")
	@Test
	void allSearchParameterTest() {
		// given
		BlogSearchRequest request = new BlogSearchRequest("query", Sort.ACCURACY, 1, 1);
		URI expectedUri = URI.create("https://example.com?query=query&sort=accuracy&page=1&size=1");

		// when
		URI uri = new KakaoUriFactory().uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}

	@DisplayName("정렬 조건이 ACCURACY 이면 정렬 조건을 accuracy 로 URI를 생성한다.")
	@Test
	void accuracySortParameterTest() {
		// given
		BlogSearchRequest request = new BlogSearchRequest("query", Sort.ACCURACY, 1, 1);
		URI expectedUri = URI.create("https://example.com?query=query&sort=accuracy&page=1&size=1");

		// when
		URI uri = new KakaoUriFactory().uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}

	@DisplayName("정렬 조건이 RECENCY 이면 정렬 조건을 recency 로 URI를 생성한다.")
	@Test
	void recencySortParameterTest() {
		// given
		BlogSearchRequest request = new BlogSearchRequest("query", Sort.RECENCY, 1, 1);
		URI expectedUri = URI.create("https://example.com?query=query&sort=recency&page=1&size=1");

		// when
		URI uri = new KakaoUriFactory().uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}

	@DisplayName("정렬 조건이 없으면 accuracy로 생성한다.")
	@Test
	void noSortParameterTest() {
		// given
		BlogSearchRequest request = new BlogSearchRequest("query", null, 1, 1);
		URI expectedUri = URI.create("https://example.com?query=query&sort=accuracy&page=1&size=1");

		// when
		URI uri = new KakaoUriFactory().uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}

	@DisplayName("페이지 조건이 없으면 페이지 조건의 기본값은 1이다")
	@Test
	void noPageParameterTest() {
		// given
		BlogSearchRequest request = new BlogSearchRequest("query", Sort.ACCURACY, null, 1);
		URI expectedUri = URI.create("https://example.com?query=query&sort=accuracy&page=1&size=1");

		// when
		URI uri = new KakaoUriFactory().uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}

	@DisplayName("페이지 크기 조건이 없으면 페이지 크기 조건의 기본값은 10이다.")
	@Test
	void noSizeParameterTest() {
		// given
		BlogSearchRequest request = new BlogSearchRequest("query", Sort.ACCURACY, 1, null);
		URI expectedUri = URI.create("https://example.com?query=query&sort=accuracy&page=1&size=10");

		// when
		URI uri = new KakaoUriFactory().uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}

	@DisplayName("기본값을 통해 검색")
	@Test
	void noSearchParameterTest() {
		// given
		BlogSearchRequest request = new BlogSearchRequest("query", null, null, null);
		URI expectedUri = URI.create("https://example.com?query=query&sort=accuracy&page=1&size=10");

		// when
		URI uri = new KakaoUriFactory().uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}
}
