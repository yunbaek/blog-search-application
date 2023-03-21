package com.yunbaek.blogsearchapplication.client;

import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.client.dto.kakao.BlogSearchResult;

public interface BlogSearchClient {
	BlogSearchResult search(BlogSearchRequest request);

	void setNextClient(BlogSearchClient client);

}
