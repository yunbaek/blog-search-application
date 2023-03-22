package com.yunbaek.blogsearchapplication.ui.dto;

import com.yunbaek.blogsearchapplication.domain.HitCount;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HitCountRankResponse {

	private String keyword;
	private long hitCount;

	private HitCountRankResponse(String keyword, long hitCount) {
		this.keyword = keyword;
		this.hitCount = hitCount;
	}

	public static HitCountRankResponse from(HitCount hitcount) {
		return new HitCountRankResponse(hitcount.getKeyword(), hitcount.getCount());
	}
}
