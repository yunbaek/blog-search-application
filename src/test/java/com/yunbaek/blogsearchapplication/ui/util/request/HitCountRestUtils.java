package com.yunbaek.blogsearchapplication.ui.util.request;

import static com.yunbaek.blogsearchapplication.ui.util.request.BlogRestUtils.*;
import static org.springframework.http.MediaType.*;

import com.yunbaek.blogsearchapplication.client.dto.Sort;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class HitCountRestUtils {
	public static ExtractableResponse<Response> 상위_검색어_요청() {
		return RestAssured.given()
			.log().all()
			.accept(APPLICATION_JSON_VALUE)
			.get("http://localhost/keyword-rank")
			.then().log().all().
			extract();
	}

	/*
	 * keyword1 : 1 부터 count 까지 keywordN 을 N 번 조회
	 */
	public static void keywordN_을_N_번_조회(int count) {
		for (int i = 0; i < count + 1; i++) {
			for (int j = 0; j < i; j++) {
				Blog_조회_요청("keyword" + i, 10, 1, Sort.ACCURACY);
			}
		}
	}
}
