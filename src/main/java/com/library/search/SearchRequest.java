package com.library.search;

import com.library.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import static com.library.config.CategoryProperty.Category;

@Data
public class SearchRequest {

	/**
	 * 검색을 원하는 질의어
	 */
	private String query;

	/**
	 * 결과 문서 정렬 방식
	 */
	private Sort sort;

	/**
	 * 결과 페이지 번호
	 */
	@Range(min = 1, max = 50)
	private Integer page;

	/**
	 * 한 페이지에 보여질 문서의 개수
	 */
	@Range(min = 1, max = 50)
	private Integer size;

	/**
	 * 검색 필드 제한
	 */
	private Target target;

	/**
	 * 카테고리 필터링
	 */
	private Category category;

	@Getter
	@AllArgsConstructor
	public enum Sort implements Constant {
		ACCURACY("accuracy", "정확도순"),
		RECENCY("recency", "최신순"),
		SALES("sales", "판매량순");
		private String code;
		private String desc;
	}

	@Getter
	@AllArgsConstructor
	public enum Target implements Constant {
		ALL("all", "전체"),
		TITLE("title", "제목"),
		ISBN("isbn", "ISBN"),
		KEYWORD("keyword", "주제어"),
		CONTENTS("contents", "목차"),
		OVERVIEW("overview", "책소개"),
		PUBLISHER("publisher", "출판사");
		private String code;
		private String desc;
	}

}
