package com.softeer.team6four.global.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ListResponse<T> {
	private final List<T> content;
	private final int size;

	@Builder
	private ListResponse(List<T> content, int size) {
		this.content = content;
		this.size = size;
	}

	public static <T> ListResponse<T> of(List<T> list) {
		return new ListResponse<>(list, list.size());
	}
}
