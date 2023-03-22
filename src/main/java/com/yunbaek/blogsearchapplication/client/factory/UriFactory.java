package com.yunbaek.blogsearchapplication.client.factory;

import java.net.URI;

import org.springframework.web.util.UriBuilder;

import com.yunbaek.blogsearchapplication.ui.dto.BlogSearchRequest;

public interface UriFactory {
	URI uri(BlogSearchRequest request, UriBuilder builder);
}
