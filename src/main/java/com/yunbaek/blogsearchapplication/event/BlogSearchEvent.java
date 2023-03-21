package com.yunbaek.blogsearchapplication.event;

import org.springframework.context.ApplicationEvent;

public class BlogSearchEvent extends ApplicationEvent {

	private final String keyword;

	public BlogSearchEvent(Object source, String keyword) {
		super(source);
		this.keyword = keyword;
	}

	public String getKeyword() {
		return keyword;
	}
}
