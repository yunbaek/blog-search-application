package com.yunbaek.blogsearchapplication.client;

import org.springframework.cache.annotation.Cacheable;

import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.ui.dto.BlogSearchResponse;

public abstract class AbstractBlogSearchClient implements BlogSearchClient {
	private BlogSearchClient nextClient;

	@Override
	@Cacheable(value = "blogList", key = "#request")
	public BlogSearchResponse search(BlogSearchRequest request) {
		try {
			return handleSearch(request);
		} catch (Exception e) {
			BlogSearchClient next = getNextClient();
			if (next != null) {
				return next.search(request);
			} else {
				throw e;
			}
		}
	}

	abstract BlogSearchResponse handleSearch(BlogSearchRequest request);

	@Override
	public void setNextClient(BlogSearchClient nextClient) {
		this.nextClient = nextClient;
	}

	public BlogSearchClient getNextClient() {
		return nextClient;
	}
}
