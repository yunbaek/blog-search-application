package com.yunbaek.blogsearchapplication.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BlogSearchEventListener {


	@EventListener
	public void handleBlogSearchEvent(BlogSearchEvent blogSearchEvent) {
	}
}
