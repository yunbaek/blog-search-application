package com.yunbaek.blogsearchapplication.ui;

import javax.validation.Valid;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yunbaek.blogsearchapplication.application.BlogSearchService;
import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.ui.dto.BlogSearchResponse;

@RestController
public class BlogSearchController {

	private final BlogSearchService blogSearchService;

	public BlogSearchController(BlogSearchService blogSearchService) {
		this.blogSearchService = blogSearchService;
	}

	@Cacheable(value = "blogList", key = "#request")
	@GetMapping("/blogs")
	@ResponseStatus(HttpStatus.OK)
	public BlogSearchResponse getResults(@Valid BlogSearchRequest request) {
		return blogSearchService.search(request);
	}
}
