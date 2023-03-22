package com.yunbaek.blogsearchapplication.client.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yunbaek.blogsearchapplication.client.dto.kakao.Document;
import com.yunbaek.blogsearchapplication.client.dto.kakao.KakaoBlogSearchResult;
import com.yunbaek.blogsearchapplication.ui.dto.BlogResponse;
import com.yunbaek.blogsearchapplication.ui.dto.BlogSearchRequest;
import com.yunbaek.blogsearchapplication.ui.dto.BlogSearchResponse;

@Mapper(componentModel = "spring")
public interface ResponseFromKakaoMapper
	extends FromTwoSourcesMapper<KakaoBlogSearchResult, BlogSearchRequest, BlogSearchResponse> {

	@Mapping(source = "blogname", target = "blogName")
	@Mapping(source = "thumbnail", target = "thumbnailUrl")
	@Mapping(source = "datetime", target = "createTime")
	BlogResponse from(Document document);

	@Override
	@Mapping(source = "searchResult.documents", target = "blogs")
	@Mapping(source = "searchResult.meta.totalCount", target = "total")
	@Mapping(source = "searchRequest.page", target = "currentPage")
	@Mapping(source = "searchRequest.size", target = "size")
	BlogSearchResponse from(KakaoBlogSearchResult searchResult, BlogSearchRequest searchRequest);
}
