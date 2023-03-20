package com.yunbaek.blogsearchapplication.client.dto;

public enum Sort {
	ACCURACY("accuracy", "sim"),
	RECENCY("recency", "date");

	private String kakaoSort;
	private String naverSort;

	Sort(String kakaoSort, String naverSort) {
		this.kakaoSort = kakaoSort;
		this.naverSort = naverSort;
	}

	public String getKakaoSort() {
		return kakaoSort;
	}

	public String getNaverSort() {
		return naverSort;
	}
}
