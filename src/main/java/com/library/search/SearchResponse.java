package com.library.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.constant.YesOrNo;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse {

	private Meta meta;
	private List<Document> documents;

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Meta {

		/**
		 * 검색어에 검색된 문서수
		 */
		@JsonProperty("total_count")
		private int totalCount;

		/**
		 * total_count 중에 노출가능 문서수
		 */
		@JsonProperty("pageable_count")
		private int pageableCount;

		/**
		 * 현재 페이지가 마지막 페이지인지 여부. 값이 false이면 page를 증가시켜 다음 페이지를 요청할 수 있음
		 */
		@JsonProperty("is_end")
		private boolean isEnd;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Document {

		/**
		 * 도서 제목
		 */
		private String title;

		/**
		 * 도서 소개
		 */
		private String contents;

		/**
		 * 책 링크 (URL)
		 */
		private String url;

		/**
		 * ISBN 번호. 국제 표준 도서번호(ISBN10,ISBN13) (ISBN10,ISBN13 중에 하나 이상 존재하면 ' '(공백)을 구분자로 출렴됨)
		 */
		private String isbn;

		/**
		 * 도서 출판날짜. ISO 8601. [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
		 */
		@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
		private Date datetime;

		/**
		 * 도서 저자 리스트
		 */
		private List<String> authors;

		/**
		 * 출판사
		 */
		private String publisher;

		/**
		 * 번역자 리스트
		 */
		private List<String> translators;

		/**
		 * 도서 정가
		 */
		private int price;

		/**
		 * 도서 판매가
		 */
		@JsonProperty("sale_price")
		private int salePrice;

		/**
		 * 도서 판매 여부
		 */
		@JsonProperty("sale_yn")
		private YesOrNo saleYn;

		/**
		 * 도서 카테고리 정보
		 */
		private String category;

		/**
		 * 도서 표지 썸네일
		 */
		private String thumbnail;

		/**
		 * 교보문고 바코드 정보
		 */
		private String barcode;

		/**
		 * 교보문고 전자책 바코드 정보
		 */
		@JsonProperty("ebook_barcode")
		private String ebookBarcode;

		/**
		 * 도서 상태 정보(정상, 품절, 절판 등). 변경 가능성이 있으니 노출용으로만 사용 권장
		 */
		private String status;
	}

}
