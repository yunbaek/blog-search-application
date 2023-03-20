package com.yunbaek.blogsearchapplication.client.factory;

import static org.assertj.core.api.Assertions.*;

import java.net.URI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.client.dto.Sort;

@DisplayName("네이버 URI Factory 테스트")
class NaverUriFactoryTest {

	private UriComponentsBuilder uriComponentsBuilder;

	@BeforeEach
	void setUp() {
		uriComponentsBuilder = UriComponentsBuilder.fromUriString("https://example.com");
	}

	@DisplayName("모든 검색 조건이 있으면 정상적으로 URI를 생성한다.")
	@Test
	void allSearchParameterTest() {
		// given
		NaverUriFactory naverUriFactory = new NaverUriFactory();
		BlogSearchRequest request = new BlogSearchRequest("query", Sort.ACCURACY, 1, 1);
		URI expectedUri = URI.create("https://example.com?sort=sim&start=1&display=1&query=query");

		// when
		URI uri = naverUriFactory.uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}

	@DisplayName("정렬 조건이 ACCURACY 이면 정렬 조건을 sim 로 URI를 생성한다.")
	@Test
	void accuracySortParameterTest() {
		// given
		NaverUriFactory naverUriFactory = new NaverUriFactory();
		BlogSearchRequest request = new BlogSearchRequest("query", Sort.ACCURACY, 1, 1);
		URI expectedUri = URI.create("https://example.com?sort=sim&start=1&display=1&query=query");

		// when
		URI uri = naverUriFactory.uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}

	@DisplayName("정렬 조건이 RECENCY 이면 정렬 조건을 date 로 URI를 생성한다.")
	@Test
	void recencySortParameterTest() {
		// given
		NaverUriFactory naverUriFactory = new NaverUriFactory();
		BlogSearchRequest request = new BlogSearchRequest("query", Sort.RECENCY, 1, 1);
		URI expectedUri = URI.create("https://example.com?sort=date&start=1&display=1&query=query");

		// when
		URI uri = naverUriFactory.uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}

	@DisplayName("정렬 조건이 없으면 정렬 조건 없이 URI를 생성한다.")
	@Test
	void noSortParameterTest() {
		// given
		NaverUriFactory naverUriFactory = new NaverUriFactory();
		BlogSearchRequest request = new BlogSearchRequest("query", null, 1, 1);
		URI expectedUri = URI.create("https://example.com?start=1&display=1&query=query");

		// when
		URI uri = naverUriFactory.uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}

	@DisplayName("페이지 번호가 없으면 페이지 번호 없이 URI를 생성한다.")
	@Test
	void noPageParameterTest() {
		// given
		NaverUriFactory naverUriFactory = new NaverUriFactory();
		BlogSearchRequest request = new BlogSearchRequest("query", Sort.ACCURACY, null, 1);
		URI expectedUri = URI.create("https://example.com?sort=sim&display=1&query=query");

		// when
		URI uri = naverUriFactory.uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}

	@DisplayName("페이지 크기 조건이 없으면 페이지 크기 없이 URI를 생성한다.")
	@Test
	void noSizeParameterTest() {
		// given
		NaverUriFactory naverUriFactory = new NaverUriFactory();
		BlogSearchRequest request = new BlogSearchRequest("query", Sort.ACCURACY, 1, null);
		URI expectedUri = URI.create("https://example.com?sort=sim&start=1&query=query");

		// when
		URI uri = naverUriFactory.uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}

	@DisplayName("검색어를 제외한 모든 조건이 없으면 검색어만 있는 URI를 생성한다.")
	@Test
	void noSearchParameterTest() {
		// given
		NaverUriFactory naverUriFactory = new NaverUriFactory();
		BlogSearchRequest request = new BlogSearchRequest("query", null, null, null);
		URI expectedUri = URI.create("https://example.com?query=query");

		// when
		URI uri = naverUriFactory.uri(request, uriComponentsBuilder);

		// then
		assertThat(uri).isEqualTo(expectedUri);
	}

}