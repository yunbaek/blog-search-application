package com.yunbaek.blogsearchapplication.ui.util;

import static com.yunbaek.blogsearchapplication.ui.util.request.BlogRestUtils.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.yunbaek.blogsearchapplication.client.dto.Sort;
import com.yunbaek.blogsearchapplication.ui.AcceptanceTest;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@DisplayName("블로그 검색 인수 테스트")
class BlogSearchAbstractTest extends AcceptanceTest {

	@BeforeEach
	public void setUp() {
		super.setUp();
	}

	@DisplayName("블로그를 검색 할 수 있다.")
	@Test
	void searchBlog() {
		// when
		ExtractableResponse<Response> 조회_요청 = Blog_조회_요청("test", 10, 1, Sort.ACCURACY);
		// then
		assertThat(조회_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	@DisplayName("검색어가 없을 경우 예외가 발생한다.")
	@Test
	void searchBlogWithoutKeyword() {
		// when
		ExtractableResponse<Response> 조회_요청 = Blog_조회_요청("", 10, 1, Sort.ACCURACY);
		// then
		BAD_REQUEST_에러_발생(조회_요청);
	}

	@DisplayName("페이지 번호가 없을 경우 1번 페이지를 조회한다.")
	@Test
	void searchBlogWithoutPage() {
		// when
		ExtractableResponse<Response> 조회_요청 = Blog_조회_요청("test", 10, Sort.ACCURACY);
		// then
		assertThat(조회_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(조회_요청.jsonPath().getInt("currentPage")).isEqualTo(1);
	}

	@DisplayName("페이지 번호가 50 이상일 경우 예외가 발생한다.")
	@Test
	void searchBlogWithPageOver50() {
		// when
		ExtractableResponse<Response> 조회_요청 = Blog_조회_요청("test", 10, 51, Sort.ACCURACY);
		// then
		BAD_REQUEST_에러_발생(조회_요청);
	}

	@DisplayName("페이지 번호가 0 이하일 경우 예외가 발생한다.")
	@Test
	void searchBlogWithPageUnder0() {
		// when
		ExtractableResponse<Response> 조회_요청 = Blog_조회_요청("test", 10, 0, Sort.ACCURACY);
		// then
		BAD_REQUEST_에러_발생(조회_요청);
	}

	@DisplayName("페이지당 블로그 수가 없을 경우 10개를 조회한다.")
	@Test
	void searchBlogWithoutSize() {
		// when
		ExtractableResponse<Response> 조회_요청 = Blog_조회_요청("test", Sort.ACCURACY);
		// then
		assertThat(조회_요청.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(조회_요청.jsonPath().getInt("size")).isEqualTo(10);
	}

	@DisplayName("페이지당 블로그 수가 51 이상일 경우 예외가 발생한다.")
	@Test
	void searchBlogWithSizeOver51() {
		// when
		ExtractableResponse<Response> 조회_요청 = Blog_조회_요청("test", 51, 1, Sort.ACCURACY);
		// then
		BAD_REQUEST_에러_발생(조회_요청);
	}

	@DisplayName("페이지당 블로그 수가 0 이하일 경우 예외가 발생한다.")
	@Test
	void searchBlogWithSizeUnder0() {
		// when
		ExtractableResponse<Response> 조회_요청 = Blog_조회_요청("test", 0, 1, Sort.ACCURACY);
		// then
		BAD_REQUEST_에러_발생(조회_요청);
	}


}
