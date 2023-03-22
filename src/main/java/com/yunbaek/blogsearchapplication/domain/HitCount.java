package com.yunbaek.blogsearchapplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.Assert;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class HitCount extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, nullable = false)
	private String keyword;

	@Column(nullable = false)
	private long count;

	private HitCount(String keyword, long count) {
		Assert.hasText(keyword, "keyword must not be empty");
		this.keyword = keyword;
		this.count = count;
	}

	public static HitCount from(String keyword) {
		return new HitCount(keyword, 1);
	}

	public String getKeyword() {
		return keyword;
	}

	public long getCount() {
		return count;
	}

	public void increaseHitCount() {
		this.count += 1;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		HitCount hitCount = (HitCount)o;

		return id == hitCount.id;
	}

	@Override
	public int hashCode() {
		return (int)(id ^ (id >>> 32));
	}

}
