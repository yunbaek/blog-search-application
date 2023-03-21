package com.yunbaek.blogsearchapplication.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yunbaek.blogsearchapplication.client.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.client.dto.naver.Item;
import com.yunbaek.blogsearchapplication.client.dto.naver.NaverBlogSearchResult;
import com.yunbaek.blogsearchapplication.ui.dto.BlogResponse;
import com.yunbaek.blogsearchapplication.ui.dto.BlogSearchResponse;

@Mapper(componentModel = "spring")
public interface ResponseFromNaverMapper
	extends FromTwoSourcesMapper<NaverBlogSearchResult, BlogSearchRequest, BlogSearchResponse> {
	@Mapping(source = "title", target = "title")
	@Mapping(source = "description", target = "contents")
	@Mapping(source = "bloggerlink", target = "url")
	@Mapping(source = "bloggername", target = "blogName")
	@Mapping(source = "postdate", target = "createTime")
	BlogResponse from(Item item);

	@Override
	@Mapping(source = "searchResult.items", target = "blogs")
	@Mapping(source = "searchResult.total", target = "total")
	@Mapping(source = "searchRequest.page", target = "currentPage")
	@Mapping(source = "searchRequest.size", target = "size")
	BlogSearchResponse from(NaverBlogSearchResult searchResult, BlogSearchRequest searchRequest);
}
