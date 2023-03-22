package com.yunbaek.blogsearchapplication.ui;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yunbaek.blogsearchapplication.application.BlogSearchService;
import com.yunbaek.blogsearchapplication.application.HitCountService;
import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.ui.dto.BlogSearchResponse;
import com.yunbaek.blogsearchapplication.ui.dto.HitCountRankResponse;

@RestController
public class BlogSearchController {

	private final BlogSearchService blogSearchService;
	private final HitCountService hitCountService;

	public BlogSearchController(BlogSearchService blogSearchService, HitCountService hitCountService) {
		this.blogSearchService = blogSearchService;
		this.hitCountService = hitCountService;
	}

	@GetMapping("/blogs")
	@ResponseStatus(HttpStatus.OK)
	public BlogSearchResponse getResults(@Valid BlogSearchRequest request) {
		return blogSearchService.search(request);
	}

	@Cacheable(value = "keywordRank")
	@GetMapping("/keyword-rank")
	@ResponseStatus(HttpStatus.OK)
	public List<HitCountRankResponse> getHitCountRank() {
		return hitCountService.getHitCountRank();
	}
}
