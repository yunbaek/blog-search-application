package com.yunbaek.blogsearchapplication.client;

import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.ui.dto.BlogSearchResponse;

public interface BlogSearchClient {
	BlogSearchResponse search(BlogSearchRequest request);

	void setNextClient(BlogSearchClient client);

}
