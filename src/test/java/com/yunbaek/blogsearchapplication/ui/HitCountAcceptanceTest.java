package com.yunbaek.blogsearchapplication.ui;

import static com.yunbaek.blogsearchapplication.ui.util.request.HitCountRestUtils.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@DisplayName("키워드 검색 횟수 인수 테스트")
class HitCountAcceptanceTest extends AcceptanceTest {
	@BeforeEach
	public void setUp() {
		super.setUp();
	}

	@DisplayName("키워드 검색 횟수를 조회 할 수 있다.")
	@Test
	void searchBlog() {
		// when
		ExtractableResponse<Response> 상위_검색어_요청 = 상위_검색어_요청();
		// then
		assertThat(상위_검색어_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	@DisplayName("키워드 검색 횟수가 내림차순으로 정렬되어 조회된다.")
	@Test
	void searchHitCountOrderByCountDescTest() {
		// given
		// keyword1을 1번 조회 ~ keyword10을 10번 조회
		keywordN_을_N_번_조회(10);
		// when
		ExtractableResponse<Response> 상위_검색어_요청 = 상위_검색어_요청();
		// then
		assertThat(상위_검색어_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(상위_검색어_요청.jsonPath().getList("keyword").get(0)).isEqualTo("keyword10");
		assertThat(상위_검색어_요청.jsonPath().getList("keyword").get(9)).isEqualTo("keyword1");
	}

	@DisplayName("키워드 검색 횟수가 10개 이상일 경우 10개만 조회된다.")
	@Test
	void searchHitCountTenTest() {
		// given
		// keyword1을 1번 조회 ~ keyword10을 10번 조회
		keywordN_을_N_번_조회(20);
		// when
		ExtractableResponse<Response> 상위_검색어_요청 = 상위_검색어_요청();
		// then
		assertThat(상위_검색어_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(상위_검색어_요청.jsonPath().getList("keyword").get(0)).isEqualTo("keyword20");
		assertThat(상위_검색어_요청.jsonPath().getList("keyword").get(9)).isEqualTo("keyword11");
	}
}
