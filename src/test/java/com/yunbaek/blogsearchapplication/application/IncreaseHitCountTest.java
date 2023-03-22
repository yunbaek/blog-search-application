package com.yunbaek.blogsearchapplication.application;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yunbaek.blogsearchapplication.domain.HitCountRepository;

@DisplayName("조회수 증가 테스트")
@SpringBootTest
class IncreaseHitCountTest {

	@Autowired
	private HitCountRepository hitCountRepository;

	@Autowired
	private HitCountService hitCountService;

	@DisplayName("HitCountService 의 increaseHitCount 메서드 테스트")
	@Test
	void increaseHitCountTest() {
		String keyword = "test";
		hitCountService.increaseHitCount(keyword);
		assertEquals(1L, hitCountRepository.findByKeyword(keyword).get().getCount());
	}

	@Test
	@DisplayName("HitCountService 의 increaseHitCount 메서드 동시성 테스트")
	void concurrencyDecreaseTest() throws InterruptedException {
		//given
		int threadCount = 100;
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		CountDownLatch latch = new CountDownLatch(threadCount);

		String keyword = "keyword";
		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					hitCountService.increaseHitCount(keyword);
				} finally {
					latch.countDown();
				}
			});
		}

		//when
		latch.await();

		//then
		assertEquals(100L, hitCountRepository.findByKeyword(keyword).get().getCount());
	}

}
