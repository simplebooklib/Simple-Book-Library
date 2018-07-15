package com.library.support;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * 페이징 형식의 응답
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageResultResponse<T> {

	private int page;
	private int size;
	private long count;
	private List<T> elements;

	public boolean isEnd() {
		return ((int) Math.ceil(this.count / (this.size * 1.0))) == this.page;
	}

}
