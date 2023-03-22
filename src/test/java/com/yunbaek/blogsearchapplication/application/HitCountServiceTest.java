package com.yunbaek.blogsearchapplication.application;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yunbaek.blogsearchapplication.domain.HitCount;
import com.yunbaek.blogsearchapplication.domain.HitCountRepository;

@DisplayName("HitCountService 테스트")
@ExtendWith(MockitoExtension.class)
class HitCountServiceTest {

	@InjectMocks
	private HitCountService hitCountService;

	@Mock
	private HitCountRepository hitCountRepository;

	@DisplayName("키워드가 존재할 경우 카운트 증가 테스트")
	@Test
	void testIncreaseHitCount_whenKeywordExists() {
		// Given
		String keyword = "test";
		HitCount hitCount = HitCount.from(keyword);
		when(hitCountRepository.findByKeyword(keyword)).thenReturn(Optional.of(hitCount));

		// When
		hitCountService.increaseHitCount(keyword);

		// Then
		verify(hitCountRepository, times(1)).findByKeyword(keyword);
		verify(hitCountRepository, times(0)).save(any(HitCount.class));
	}

	@DisplayName("키워드가 존재하지 않을 경우 카운트 생성 테스트")
	@Test
	void testIncreaseHitCount_whenKeywordDoesNotExist() {
		// Given
		String keyword = "test";
		when(hitCountRepository.findByKeyword(keyword)).thenReturn(Optional.empty());

		// When
		hitCountService.increaseHitCount(keyword);

		// Then
		verify(hitCountRepository, times(1)).findByKeyword(keyword);
		verify(hitCountRepository, times(1)).save(any(HitCount.class));
	}

	@DisplayName("랭킹 조회 테스트")
	@Test
	void testGetHitCountRank() {
		// Given
		when(hitCountRepository.findTop10ByOrderByCountDesc()).thenReturn(List.of());

		// When
		hitCountService.getHitCountRank();

		// Then
		verify(hitCountRepository, times(1)).findTop10ByOrderByCountDesc();
	}
}

