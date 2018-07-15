package com.library.book;

import com.library.config.CategoryProperty;
import com.library.search.SearchRequest;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Data
public class BookSearchRequest {

	/**
	 * 검색을 원하는 질의어
	 */
	private String query;

	/**
	 * 결과 문서 정렬 방식
	 */
	private SearchRequest.Sort sort;

	/**
	 * 결과 페이지 번호
	 */
	@Range(min = 1, max = 50)
	private int page = 1;

	/**
	 * 한 페이지에 보여질 문서의 개수
	 */
	@Range(min = 1, max = 50)
	private int size = 10;

	/**
	 * 검색 필드 제한
	 */
	private SearchRequest.Target target;

	private String catName;

	private Integer catNo;

	public String toUri(String uri) {
		return UriComponentsBuilder.fromUriString(uri)
				.queryParam("query", this.query)
				.queryParam("page", this.page)
				.queryParam("sort", this.sort)
				.queryParam("size", this.size)
				.queryParam("target", this.target)
				.queryParam("catName", this.catName)
				.queryParam("catNo", this.catNo)
				.build().toUriString();
	}

	public SearchRequest toSearchRequest(CategoryProperty categoryProperty) {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setQuery(this.query);
		searchRequest.setSort(this.sort);
		searchRequest.setTarget(this.target);
		searchRequest.setPage(this.page);
		searchRequest.setSize(this.size);
		searchRequest.setCategory(Optional.ofNullable(this.catNo)
				.map(categoryProperty::valueOf)
				.orElse(null));
		return searchRequest;
	}

}
