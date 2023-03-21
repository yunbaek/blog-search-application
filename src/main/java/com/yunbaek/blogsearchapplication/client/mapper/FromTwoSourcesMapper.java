package com.yunbaek.blogsearchapplication.client.mapper;

public interface FromTwoSourcesMapper<S1, S2, T> {

	T from(S1 source1, S2 source2);
}
