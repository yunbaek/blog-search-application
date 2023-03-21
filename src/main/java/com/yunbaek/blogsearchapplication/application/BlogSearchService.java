package com.yunbaek.blogsearchapplication.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yunbaek.blogsearchapplication.client.BlogSearchClient;
import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.client.dto.kakao.BlogSearchResult;

@Service
public class BlogSearchService {
	private final List<BlogSearchClient> clients;

	public BlogSearchService(List<BlogSearchClient> clients) {
		this.clients = clients;
		setNextClients();
	}

	public BlogSearchResult search(BlogSearchRequest request) {
		return clients.get(0).search(request);
	}

	private void setNextClients() {
		for (int i = 0; i < clients.size() - 1; i++) {
			clients.get(i).setNextClient(clients.get(i + 1));
		}
	}

}
