package com.yunbaek.blogsearchapplication.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.yunbaek.blogsearchapplication.application.HitCountService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BlogSearchEventListener {

	private final HitCountService hitCountService;

	@EventListener
	public void handleBlogSearchEvent(BlogSearchEvent blogSearchEvent) {
		String keyword = blogSearchEvent.getKeyword();
		hitCountService.increaseHitCount(keyword);
	}
}
