package com.yunbaek.blogsearchapplication.ui.util.request;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.MediaType.*;

import org.springframework.http.HttpStatus;

import com.yunbaek.blogsearchapplication.client.dto.Sort;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class BlogRestUtils {

	public static ExtractableResponse<Response> Blog_조회_요청(String query, int size, int page, Sort sort) {
		return RestAssured.given()
			.queryParam("query", query)
			.queryParam("size", size)
			.queryParam("page", page)
			.queryParam("sort", sort)
			.log().all()
			.accept(APPLICATION_JSON_VALUE)
			.contentType(APPLICATION_JSON_VALUE)
			.get("http://localhost/blogs")
			.then().log().all().
			extract();
	}

	public static ExtractableResponse<Response> Blog_조회_요청(String query, int size, Sort sort) {
		return RestAssured.given()
			.queryParam("query", query)
			.queryParam("size", size)
			.queryParam("sort", sort)
			.log().all()
			.accept(APPLICATION_JSON_VALUE)
			.contentType(APPLICATION_JSON_VALUE)
			.get("http://localhost/blogs")
			.then().log().all().
			extract();
	}

	public static ExtractableResponse<Response> Blog_조회_요청(String query, Sort sort) {
		return RestAssured.given()
			.queryParam("query", query)
			.queryParam("sort", sort)
			.log().all()
			.accept(APPLICATION_JSON_VALUE)
			.contentType(APPLICATION_JSON_VALUE)
			.get("http://localhost/blogs")
			.then().log().all().
			extract();
	}


	public static void BAD_REQUEST_에러_발생(ExtractableResponse<Response> 조회_요청) {
		assertThat(조회_요청.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}


}
