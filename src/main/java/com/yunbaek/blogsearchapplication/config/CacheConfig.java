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

		// 블로그 목록 캐시
		ConcurrentMapCache blogListCache = new ConcurrentMapCache(BLOG_LIST_CACHE, Caffeine.newBuilder()
			.expireAfterWrite(30, TimeUnit.SECONDS)
			.maximumSize(500)
			.build()
			.asMap(), false);

		// 검색어 순위 캐시
		ConcurrentMapCache keywordRankCache = new ConcurrentMapCache(KEYWORD_RANK_CACHE, Caffeine.newBuilder()
			.expireAfterWrite(10, TimeUnit.SECONDS)
			.maximumSize(1)
			.build()
			.asMap(), false);

		// 검색어 생성 캐시
		ConcurrentMapCache keywordCreateCache = new ConcurrentMapCache(KEYWORD_INSERT_CACHE, Caffeine.newBuilder()
			.expireAfterWrite(10, TimeUnit.SECONDS)
			.maximumSize(10)
			.build()
			.asMap(), false);

		cacheManager.setCaches(List.of(blogListCache, keywordRankCache, keywordCreateCache));

		return cacheManager;
	}

}
