package com.yunbaek.blogsearchapplication.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@DisplayName("HitCount 도메인 테스트")
class HitCountTest {

	@DisplayName("HitCount 생성 테스트")
	@Test
	void createHitCountTest() {
		assertThatCode(
				() -> HitCount.from("test"))
			.doesNotThrowAnyException();
	}

	@DisplayName("HitCount 의 키워드가 null 이거나 빈 문자열일 경우 예외 발생 테스트")
	@ParameterizedTest
	@NullAndEmptySource
	void createHitCountWithEmptyKeywordTest(String keyword) {
		assertThatThrownBy(
				() -> HitCount.from(keyword))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("HitCount 의 초기 count 는 0 테스트")
	@Test
	void initialCountTest() {
		HitCount hitCount = HitCount.from("test");
		assertThat(hitCount.getCount()).isZero();
	}

}
