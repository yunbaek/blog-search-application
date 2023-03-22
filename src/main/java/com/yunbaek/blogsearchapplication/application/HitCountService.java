package com.yunbaek.blogsearchapplication.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunbaek.blogsearchapplication.application.lock.Lock;
import com.yunbaek.blogsearchapplication.domain.HitCount;
import com.yunbaek.blogsearchapplication.domain.HitCountRepository;
import com.yunbaek.blogsearchapplication.ui.dto.HitCountRankResponse;

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

	@Transactional(readOnly = true)
	public List<HitCountRankResponse> getHitCountRank() {
		 return hitCountRepository.findTop10ByOrderByCountDesc()
			 .stream()
			 .map(HitCountRankResponse::from)
			 .collect(Collectors.toList());
	}
}
