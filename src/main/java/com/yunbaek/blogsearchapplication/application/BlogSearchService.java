package com.yunbaek.blogsearchapplication.application;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.yunbaek.blogsearchapplication.client.BlogSearchClient;
import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.ui.dto.BlogSearchResponse;

@Service
public class BlogSearchService {
	private final List<BlogSearchClient> clients;
	private final ApplicationEventPublisher eventPublisher;

	public BlogSearchService(List<BlogSearchClient> clients, ApplicationEventPublisher eventPublisher) {
		this.clients = clients;
		this.eventPublisher = eventPublisher;
		setNextClients();
	}

	public BlogSearchResponse search(BlogSearchRequest request) {
		eventPublisher.publishEvent(request.getQuery());
		return clients.get(0).search(request);
	}

	private void setNextClients() {
		for (int i = 0; i < clients.size() - 1; i++) {
			clients.get(i).setNextClient(clients.get(i + 1));
		}
	}

}
