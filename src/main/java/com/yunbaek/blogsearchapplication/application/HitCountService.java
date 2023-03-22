package com.yunbaek.blogsearchapplication.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunbaek.blogsearchapplication.application.lock.Lock;
import com.yunbaek.blogsearchapplication.domain.HitCount;
import com.yunbaek.blogsearchapplication.domain.HitCountRepository;

@Service
public class HitCountService {

	private final HitCountRepository hitCountRepository;

	public HitCountService(HitCountRepository hitCountRepository) {
		this.hitCountRepository = hitCountRepository;
	}

	@Transactional
	@Lock
	public void increaseHitCount(String keyword) {
		hitCountRepository.findByKeyword(keyword)
			.ifPresentOrElse(
				HitCount::increaseHitCount,
				() -> hitCountRepository.saveAndFlush(HitCount.from(keyword))
			);
	}

}
