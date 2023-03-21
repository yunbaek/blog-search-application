package com.yunbaek.blogsearchapplication.client.mapper;

public interface FromSourceMapper<S, T> {
	T from(S source);
}
