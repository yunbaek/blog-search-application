package com.yunbaek.blogsearchapplication.config;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

	public static final String BLOG_LIST_CACHE = "blogList";

	public static final String KEYWORD_RANK_CACHE = "keywordRank";
	public static final String KEYWORD_INSERT_CACHE = "keywordInsert";

	@Override
	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();

		/*
		 블로그 목록 캐시
		 30초 동안 캐시 유지
		 500개까지 캐시 유지
		 */
		ConcurrentMapCache blogListCache = getConcurrentMapCache(BLOG_LIST_CACHE, 30, 500);

		/*
		 검색어 랭킹 캐시
		 10초 동안 캐시 유지
		 1개까지 캐시 유지
		 */
		ConcurrentMapCache keywordRankCache = getConcurrentMapCache(KEYWORD_RANK_CACHE, 10, 1);

		/*
		 검색어 생성 캐시
		 10초 동안 캐시 유지
		 10개까지 캐시 유지
		 */
		ConcurrentMapCache keywordCreateCache = getConcurrentMapCache(KEYWORD_INSERT_CACHE, 10, 10);

		cacheManager.setCaches(List.of(blogListCache, keywordRankCache, keywordCreateCache));

		return cacheManager;
	}

	private static ConcurrentMapCache getConcurrentMapCache(String keywordInsertCache, int durationSecond,
		int maxSize) {
		return new ConcurrentMapCache(keywordInsertCache, Caffeine.newBuilder()
			.expireAfterWrite(durationSecond, TimeUnit.SECONDS)
			.maximumSize(maxSize)
			.build()
			.asMap(), false);
	}

}
